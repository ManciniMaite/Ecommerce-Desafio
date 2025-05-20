import { ItemCarrito } from "./ItemCarrito";
import { Usuario } from "./Usuario";

export class Carrito {
    id: string;
    itemsCarrito: ItemCarrito[];
    descuento: any; 
    subTotal: number;
    montoDescuento: number;
    total: number;
    cliente: Usuario;
    fecha: Date;
}