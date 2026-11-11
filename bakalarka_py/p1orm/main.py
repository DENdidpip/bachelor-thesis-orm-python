from sqlalchemy import create_engine, select
from sqlalchemy.orm import Session
from kniha import Base, Kniha


engine = create_engine("sqlite:///knihy.db")
Base.metadata.create_all(engine)

# Citanie vsetkych knih
def citajKnihy():
    with Session(engine) as session:
        knihy = session.scalars(select(Kniha)).all()

        for k in knihy:
            print(k)


# Zlava na vsetky knihy
def zmenaCien():
    with Session(engine) as session:
        knihy = session.scalars(select(Kniha)).all()

        for k in knihy:
            k.cena = k.cena * 0.8

        session.commit()

        for k in knihy:
            print(k)


# Vyhladanie knihy podla primarneho kluca
def najdiKnihu():
    with Session(engine) as session:
        k = session.get(Kniha, "Pipi")
        print(k)


# Vytvorenie novej knihy
def vytvorKnihu():
    k = Kniha(nazov="Pipi", cena=0.0)

    with Session(engine) as session:
        try:
            session.add(k)
            session.commit()
            print("database is ready")
        except Exception:
            session.rollback()
            raise


if __name__ == "__main__":
    #citajKnihy()
    zmenaCien()
    #vytvorKnihu()
    #najdiKnihu()
