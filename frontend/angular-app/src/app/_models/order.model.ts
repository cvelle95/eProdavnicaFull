import { ItemModel } from "./item.model";
export interface OrderModel{
    id: number;
    kolicina: number;
    cena_porudzbine: number;
    status: string;
    korisnik_id: number;
    proizvod_id: number;
    proizvod?: ItemModel;

}