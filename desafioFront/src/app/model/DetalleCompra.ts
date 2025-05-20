import { Compra } from "./Compra";
import { Producto } from "./Producto";

export class DetalleCompra{
    id: number;
    cantidad: number;
    producto: Producto;
    precioUnitario: number;
}