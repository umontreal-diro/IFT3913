#!/usr/bin/env python3
"""
Read non-registered GitHub IDs from submissions.md, fetch their contact info
from the Studium API, and produce 3 CSV files:
  tache1_missing.csv, tache2_missing.csv, tache3_missing.csv

Each file contains: github_id, fullname, email

Required environment variable:
  STUDIUM_TOKEN  — Moodle API token

Run from the repo root:
  STUDIUM_TOKEN=xxx python scripts/fetch_missing_students.py
"""

import csv
import json
import os
import re
import sys
from pathlib import Path
from urllib.parse import urlencode
from urllib.request import Request, urlopen


SUBMISSIONS_FILE = Path("submissions.md")


def fetch_enrolled_students(token):
    """Call the Studium API. Returns {lowercase_github_id: {github_id, fullname, email}}."""
    params = urlencode({
        "wstoken": token,
        "wsfunction": "core_enrol_get_enrolled_users",
        "moodlewsrestformat": "json",
        "courseid": "349530",
        "options[0][name]": "userfields",
        "options[0][value]": "firstname,lastname,email,customfields",
    }).encode()

    req = Request(
        "https://studium-uat.umontreal.ca/webservice/rest/server.php",
        data=params,
        method="POST",
    )
    with urlopen(req) as resp:
        raw = json.loads(resp.read())

    students = {}
    for s in raw:
        gh_id = None
        for cf in s.get("customfields", []):
            if cf.get("shortname") == "github" and cf.get("value"):
                gh_id = cf["value"].strip()
                break
        if not gh_id:
            continue

        students[gh_id.lower()] = {
            "github_id": gh_id,
            "fullname": s.get("fullname", ""),
            "email": s.get("email", ""),
        }
    return students


def parse_non_inscrits(filepath):
    """Parse submissions.md and return {task_number: [github_id, ...]}."""
    content = filepath.read_text(errors="replace")
    lines = content.splitlines()

    result = {}
    current_task = None

    for line in lines:
        # Detect which task section we're in
        task_match = re.match(r"^#\s*Inscriptions tâche\s*(\d+)", line)
        if task_match:
            current_task = task_match.group(1)
            continue

        # Parse the non-inscrits line (semicolon-separated GitHub IDs)
        if current_task and re.match(r"^non-inscrits\s*:", line, re.IGNORECASE):
            ids_str = line.split(":", 1)[1].strip()
            if ids_str:
                ids = [gh_id.strip() for gh_id in ids_str.split(";") if gh_id.strip()]
            else:
                ids = []
            result[current_task] = ids
            current_task = None

    return result


def write_csv(filename, students):
    """Write a CSV with github_id, fullname, email sorted by name."""
    with open(filename, "w", newline="", encoding="utf-8-sig") as f:
        writer = csv.writer(f)
        writer.writerow(["github_id", "fullname", "email"])
        for s in sorted(students, key=lambda x: x["fullname"].lower()):
            writer.writerow([s["github_id"], s["fullname"], s["email"]])


def main():
    studium_token = os.environ.get("STUDIUM_TOKEN")
    if not studium_token:
        print("Error: STUDIUM_TOKEN environment variable is required.", file=sys.stderr)
        sys.exit(1)

    if not SUBMISSIONS_FILE.exists():
        print(f"Error: {SUBMISSIONS_FILE} not found. Run the workflow first.", file=sys.stderr)
        sys.exit(1)

    # Parse the non-inscrits from submissions.md
    non_inscrits = parse_non_inscrits(SUBMISSIONS_FILE)
    if not non_inscrits:
        print("No non-inscrits sections found in submissions.md.", file=sys.stderr)
        sys.exit(1)

    # Fetch contact info from the API
    print("Fetching enrolled students from Studium API...")
    enrolled = fetch_enrolled_students(studium_token)
    print(f"  {len(enrolled)} students with a GitHub ID")

    # For each task, look up the non-registered IDs and write a CSV
    for task_num in ("1", "2", "3"):
        ids = non_inscrits.get(task_num, [])
        missing = []
        not_found = []

        for gh_id in ids:
            info = enrolled.get(gh_id.lower())
            if info:
                missing.append(info)
            else:
                # ID is in submissions.md but not in the API — include with blanks
                not_found.append(gh_id)
                missing.append({"github_id": gh_id, "fullname": "", "email": ""})

        filename = f"tache{task_num}_missing.csv"
        write_csv(filename, missing)
        print(f"  tache {task_num}: {len(missing)} missing → {filename}")
        if not_found:
            print(f"    ({len(not_found)} IDs not found in API: {', '.join(not_found)})")


if __name__ == "__main__":
    main()
