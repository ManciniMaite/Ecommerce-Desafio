import { CanActivateFn, Router } from "@angular/router";
import { CarritoService } from "../services/carrito.service";
import { inject } from "@angular/core";
import { map, take } from "rxjs";


export const CarritoGuard: CanActivateFn = () => {
  const carritoService = inject(CarritoService);
  const router = inject(Router);

  return carritoService.carrito$.pipe(
    take(1),
    map(carrito => {
      if (carrito) {
        return true;
      } else {
        router.navigate(['']); 
        return false;
      }
    })
  );
};
