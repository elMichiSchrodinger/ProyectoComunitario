import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menucli',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menucli.component.html',
  styleUrl: './menucli.component.css'
})
export class MenucliComponent {
  contadorModulo=0;
  constructor(private router: Router) {
  }
  solicitarservicio(){
    this.contadorModulo=1;
  }
  estadoservicio(){
    this.contadorModulo=2;
  }
  volverInicio(){
    this.router.navigate(['']);
  }
}
