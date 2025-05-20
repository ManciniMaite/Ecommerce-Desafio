import { DetalleCompra } from "./DetalleCompra";
import { Usuario } from "./Usuario";

export class Compra{
    id: number;
    fechaCompra: Date;
    subTotal: number;
    montoDescuento: number;
    total: number;
    detallesCompra: DetalleCompra[];
    cliente: Usuario;
}