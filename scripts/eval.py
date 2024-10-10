import os
import re
import requests

# GitHub API token from environment variables
GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')

if not GITHUB_TOKEN:
    raise EnvironmentError("GITHUB_TOKEN environment variable not set.")

# File paths to save results
no_links_file = "no_links.txt"
no_actions_file = "no_actions.txt"
successful_file = "successful.txt"
unsuccessful_file = "unsuccessful.txt"

# GitHub headers for API authentication
headers = {
    'Authorization': f'token {GITHUB_TOKEN}',
    'Accept': 'application/vnd.github.v3+json'
}

# Function to extract GitHub repo link from a README file
def extract_github_link(readme_path):
    with open(readme_path, 'r', encoding='utf-8') as file:
        content = file.read()
        # Regex to find GitHub links excluding /umontreal-diro/
        pattern = r'https://github\.com/(?!(umontreal-diro)/)[\w\-]+/[\w\-]+'
        match = re.search(pattern, content)
        return match.group(0) if match else None

# Function to get the most recent action run for test.yml
def get_latest_test_action(repo_url):
    repo_name = repo_url.replace("https://github.com/", "")
    actions_url = f"https://api.github.com/repos/{repo_name}/actions/workflows"
    response = requests.get(actions_url, headers=headers)

    if response.status_code == 200:
        workflows = response.json().get('workflows', [])
        # Find workflow with path ending in 'test.yml'
        for workflow in workflows:
            if workflow.get('path', '').endswith('test.yml'):
                return workflow['id']
    else:
        print(f"Failed to fetch workflows for {repo_name}: {response.status_code}")
    return None

# Function to check if the latest test.yml action passed
def check_action_status(repo_url, workflow_id):
    repo_name = repo_url.replace("https://github.com/", "")
    runs_url = f"https://api.github.com/repos/{repo_name}/actions/workflows/{workflow_id}/runs?per_page=1"
    response = requests.get(runs_url, headers=headers)

    if response.status_code == 200:
        runs = response.json().get('workflow_runs', [])
        if runs:
            latest_run = runs[0]
            return latest_run['conclusion'] == 'success'
    else:
        print(f"Failed to fetch workflow runs for {repo_name}: {response.status_code}")
    return None

# Write a link to the appropriate file
def write_link_to_file(file_path, link):
    with open(file_path, 'a', encoding='utf-8') as file:
        file.write(f"{link}\n")

# Main function to check all READMEs in the directories
def check_readmes(base_dir):
    for root, dirs, files in os.walk(base_dir):
        for file_name in files:
            if file_name.lower() == 'readme.md':
                readme_path = os.path.join(root, file_name)
                github_link = extract_github_link(readme_path)

                if github_link:
                    workflow_id = get_latest_test_action(github_link)
                    if workflow_id:
                        passed = check_action_status(github_link, workflow_id)
                        if passed is True:
                            write_link_to_file(successful_file, github_link)
                        elif passed is False:
                            write_link_to_file(unsuccessful_file, github_link)
                        else:
                            write_link_to_file(no_actions_file, github_link)
                    else:
                        write_link_to_file(no_actions_file, github_link)
                else:
                    # Store the README file path relative to the repository root
                    relative_path = os.path.relpath(readme_path, base_dir)
                    write_link_to_file(no_links_file, relative_path)

if __name__ == "__main__":
    base_dir = 'tache2'
    # Clear previous output files if they exist
    for filename in [no_links_file, no_actions_file, successful_file, unsuccessful_file]:
        if os.path.exists(filename):
            os.remove(filename)
    check_readmes(base_dir)
