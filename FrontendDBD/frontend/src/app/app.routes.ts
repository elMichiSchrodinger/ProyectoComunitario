import { Routes } from '@angular/router';
import {VacanteComponent} from './vacante/vacante.component';
import {LoginComponent} from './login/login.component';
import {PostulacionComponent} from './postulacion/postulacion.component';
import {DetallePostulacionComponent} from './postulacion/detalle-postulacion/detalle-postulacion.component';
import {MenuComponent} from './menu/menu.component';
import {NotificacionComponent} from './notificacion/notificacion.component';
import {
  NotificacionPersonalizadaComponent
} from './notificacion/notificacion-personalizada/notificacion-personalizada.component';
import {ListaVacantesComponent} from './lista-vacantes/lista-vacantes.component';
import {DetalleVacanteComponent} from './lista-vacantes/detalle-vacante/detalle-vacante.component';
import {
  PostulacionVacanteComponent
} from './lista-vacantes/detalle-vacante/postulacion-vacante/postulacion-vacante.component';
import {MostrarinvitacionComponent} from './componentcrm/mostrarinvitacion/mostrarinvitacion.component';
import {LogincliComponent} from './logincli/logincli.component';
import {MenucliComponent} from './componentcrm/menucli/menucli.component';
import {PreloginComponent} from './prelogin/prelogin.component';

export const routes: Routes = [
  {path:'vacante', component: VacanteComponent},
  {path:'', component: PreloginComponent},
  {path:'login', component: LoginComponent},
  {path:'logincli', component: LogincliComponent},
  {path:'menucli', component: MenucliComponent},
  {path:'postulacion', component: PostulacionComponent},
  {path:'detallePostulacion', component: DetallePostulacionComponent},
  {path:'menu',component: MenuComponent},
  {path:'notificacion',component: NotificacionComponent},
  {path:'personalizar',component:NotificacionPersonalizadaComponent},
  {path:'listaVacantes',component:ListaVacantesComponent},
  {path:'detalleVacante',component:DetalleVacanteComponent},
  {path:'postulacionVacante',component:PostulacionVacanteComponent},
  {path:'mostrarInvitacion',component:MostrarinvitacionComponent}
];
