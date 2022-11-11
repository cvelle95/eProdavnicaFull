import { FavouriteCategoryModel } from "./favouriteCategory.model"
export interface UserModel{
    id: number;
    ime: string;
    prezime: string;
    email: string;
    telefon: string;
    adresa: string;
    username: string;
    password: string;
    omiljeneKategorije?: Array<FavouriteCategoryModel>;
}