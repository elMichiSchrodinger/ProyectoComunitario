import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-prelogin',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './prelogin.component.html',
  styleUrl: './prelogin.component.css'
})
export class PreloginComponent {
  preloginemp!: string;
  prelogincli!: string;
  constructor(private router: Router) {
  }
  obtenerlogincli(){
      this.router.navigate(['logincli']);
  }
  obtenerloginemp(){
    this.router.navigate(['login']);
  }
}
