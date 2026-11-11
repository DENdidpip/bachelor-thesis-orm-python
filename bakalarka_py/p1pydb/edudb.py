import sqlite3

conn = sqlite3.connect("edudb.db")

cursor = conn.cursor()

cursor.execute("""
    CREATE TABLE kniha (
        nazov TEXT,
        cena REAL
    )
""")

cursor.execute(
    "INSERT INTO kniha (nazov, cena) VALUES (?, ?)",
    ("Pipi Dlhá Pančucha", 12.50)
)

cursor.execute(
    "INSERT INTO kniha (nazov, cena) VALUES (?, ?)",
    ("Harry Potter", 18.90)
)

conn.commit()
conn.close()

print("Databáza bola vytvorená.")