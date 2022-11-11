export interface ItemModel{
    id: number;
    naziv: string;
    opis: string;
    cena: number;
    kategorija_proizvoda_id: number;
    proizvodjac: string;
    coverImage: string;
    zemljaPorekla: string;
    kategorijaroizvodaNaziv?: string;
}