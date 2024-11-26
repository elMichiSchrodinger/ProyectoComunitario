import { Component } from '@angular/core';
import {EmpleadoService} from '../services/empleado.service';
import { Router } from '@angular/router';
import {FormsModule} from '@angular/forms';
import {Empleado} from '../models/empleado.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  correo!: string;
  password!:string;
  empleado_prueba!:Empleado;
  constructor(private empleadoService:EmpleadoService, private router:Router) {
  }
  obtenerEmpleado(){
    this.empleadoService.obtenerEmpleado(this.correo,this.password).subscribe({
      next: data => {
        this.empleado_prueba = data;
      },
      error: error => {
        alert("Correo o contraseña incorrecto");
      }
    })
    if(this.empleado_prueba){
      this.router.navigate(['menu']);
      this.empleadoService.setEmpleado(this.empleado_prueba);
    }
  }
}
