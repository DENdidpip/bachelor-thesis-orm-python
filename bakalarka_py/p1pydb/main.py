import sqlite3
from Kniha import Kniha


conn = sqlite3.connect("edudb.db")

cursor = conn.cursor()

cursor.execute("SELECT * FROM kniha")

zoznam = []

for row in cursor.fetchall():
    k = Kniha(row[0], row[1])
    zoznam.append(k)

for k in zoznam:
    print("nazov =", k.nazov, "\tcena =", k.cena)

conn.close()