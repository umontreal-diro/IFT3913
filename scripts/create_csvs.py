import pandas as pd

# Input files
info_csv = ""
github_csv = ""

# Output files
output_simple = "students.csv"
output_complete = "all_student_data.csv"
output_log = "matching_log.csv"

# Read the CSV files
info = pd.read_csv(info_csv)
github = pd.read_csv(github_csv)

# Remove accidental whitespace from column names
info.columns = info.columns.str.strip()
github.columns = github.columns.str.strip()

# Rename columns for easier handling
info = info.rename(columns={
    "Prénom": "prenom",
    "Nom de famille": "nom",
    "Matricule": "matricule",
    "Adresse de courriel": "email"
})

github = github.rename(columns={
    "Nom complet de l’utilisateur": "nom_complet",
    "Adresse de courriel": "email",
    "Quel est votre identifiant (username) GitHub ?": "github_username"
})

# Clean email addresses before joining
info["email"] = info["email"].astype(str).str.strip().str.lower()
github["email"] = github["email"].astype(str).str.strip().str.lower()

# Join using email address
merged = pd.merge(
    info,
    github[["email", "nom_complet", "github_username"]],
    on="email",
    how="inner"
)


# CSV with: Nom complet, username GitHub
simple = merged[[
    "nom_complet",
    "github_username"
]].copy()

simple.to_csv(
    output_simple,
    index=False,
    encoding="utf-8-sig"
)

# CSV with Nom complet, Matricule, Adresse de courriel, username GitHub
complete = merged[[
    "nom_complet",
    "matricule",
    "email",
    "github_username"
]].copy()

complete = complete.rename(columns={
    "email": "Adresse de courriel"
})

complete.to_csv(
    output_complete,
    index=False,
    encoding="utf-8-sig"
)

# Create separate log for students missing from either CSV

# Students in info.csv but not in github.csv
info_not_in_github = info[
    ~info["email"].isin(github["email"])
].copy()

# GitHub users in github.csv but not in info.csv
github_not_in_info = github[
    ~github["email"].isin(info["email"])
].copy()

log_rows = []

for _, row in info_not_in_github.iterrows():
    log_rows.append({
        "Type": "Not in github_csv",
        "Nom complet": f"{row['prenom']} {row['nom']}".strip(),
        "Adresse de courriel": row["email"],
        "GitHub username": ""
    })

for _, row in github_not_in_info.iterrows():
    log_rows.append({
        "Type": "Not in info_csv",
        "Nom complet": row["nom_complet"],
        "Adresse de courriel": row["email"],
        "GitHub username": row["github_username"]
    })

log = pd.DataFrame(
    log_rows,
    columns=[
        "Type",
        "Nom complet",
        "Adresse de courriel",
        "GitHub username"
    ]
)

log.to_csv(
    output_log,
    index=False,
    encoding="utf-8-sig"
)

print(f"Number of matched students: {len(merged)}")
print(f"Students who have not responded yet: {len(info_not_in_github)}")
print(f"GitHub users who are not students: {len(github_not_in_info)}")
