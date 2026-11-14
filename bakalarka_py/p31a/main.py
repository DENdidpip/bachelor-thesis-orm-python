from sqlalchemy import create_engine
from sqlalchemy.orm import Session
from Person import Base, Person

engine = create_engine("sqlite:///person.db", echo=True)
Base.metadata.create_all(engine)

def persist(person):
    with Session(engine, expire_on_commit=False) as session:
        session.add(person)
        session.commit()

def main():
    p = Person()
    p.name = "Fero"
    persist(p)
    print(p.id)

if __name__ == "__main__":
    main()
