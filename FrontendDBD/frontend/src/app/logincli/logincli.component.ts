import { Component } from '@angular/core';
import {LoginCli} from '../models/logincli.model';
import {Router} from '@angular/router';
import {LogincliService} from '../services/logincli.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-logincli',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './logincli.component.html',
  styleUrl: './logincli.component.css'
})
export class LogincliComponent {
  ruc_dni!: string;
  correo!: string;
  logincli_prueba!: LoginCli;
  constructor(private logincliService:LogincliService, private router:Router) {
  }
  obtenerLoginCli() {
    this.logincliService.obtenerLoginCli(this.ruc_dni,this.correo).subscribe({
      next: data => {
        this.logincli_prueba = data;
      },
      error: error => {
        alert("Correo o ruc_dni incorrecto");
      }
    })
    if(this.logincli_prueba){
      this.router.navigate(['menucli']);
      this.logincliService.setLoginCli(this.logincli_prueba);
    }
  }
}
