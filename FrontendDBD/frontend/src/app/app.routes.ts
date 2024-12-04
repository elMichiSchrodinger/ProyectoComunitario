import { Routes } from '@angular/router';
import { VacanteComponent } from './componentreclutamiento/vacante/vacante.component';
import { LoginComponent } from './login/login.component';
import { PostulacionComponent } from './componentreclutamiento/postulacion/postulacion.component';
import { DetallePostulacionComponent } from './componentreclutamiento/postulacion/detalle-postulacion/detalle-postulacion.component';
import { MenuComponent } from './menu/menu.component';
import { NotificacionComponent } from './componentreclutamiento/notificacion/notificacion.component';
import {
  NotificacionPersonalizadaComponent
} from './componentreclutamiento/notificacion/notificacion-personalizada/notificacion-personalizada.component';
import { ListaVacantesComponent } from './componentreclutamiento/lista-vacantes/lista-vacantes.component';
import { DetalleVacanteComponent } from './componentreclutamiento/lista-vacantes/detalle-vacante/detalle-vacante.component';
import {
  PostulacionVacanteComponent
} from './componentreclutamiento/lista-vacantes/detalle-vacante/postulacion-vacante/postulacion-vacante.component';
import { MostrarinvitacionComponent } from './componentcrm/mostrarinvitacion/mostrarinvitacion.component';
import { LogincliComponent } from './logincli/logincli.component';
import { MenucliComponent } from './componentcrm/menucli/menucli.component';
import { PreloginComponent } from './prelogin/prelogin.component';
import { MostraradjaComponent } from './componentcrm/mostraradja/mostraradja.component';
import { MostraradjrComponent } from './componentcrm/mostraradjr/mostraradjr.component';
import { ElaborarpropuestaComponent } from './componentcrm/elaborarpropuesta/elaborarpropuesta.component';
import { EquiposComponent } from './componentmantenimiento/equipos/equipos.component';
import { InfraestructurasComponent } from './componentmantenimiento/infraestructuras/infraestructuras.component';
import { CronogramasComponent } from './componentmantenimiento/cronogramas/cronogramas.component';
import { DetalleEquipoComponent } from './componentmantenimiento/detalle-equipo/detalle-equipo.component';
import { NuevoEquipoComponent } from './componentmantenimiento/nuevo-equipo/nuevo-equipo.component';
import { DetalleInfraestructuraComponent } from './componentmantenimiento/detalle-infraestructura/detalle-infraestructura.component';
import { NuevaInfraestructuraComponent } from './componentmantenimiento/nueva-infraestructura/nueva-infraestructura.component';
import {ProyectosComponent} from './componentgestionproyectos/proyectos/proyectos.component';
import {ModalComponent} from './componentgestionproyectos/modal/modal.component';
export const routes: Routes = [
  { path: 'vacante', component: VacanteComponent },
  { path: '', component: PreloginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logincli', component: LogincliComponent },
  { path: 'menucli', component: MenucliComponent },
  { path: 'postulacion', component: PostulacionComponent },
  { path: 'detallePostulacion', component: DetallePostulacionComponent },
  { path: 'menu', component: MenuComponent },
  {path:'Proyectos',component: ProyectosComponent},
  {path:'Proyectos/modal',component: ModalComponent},
  { path: 'notificacion', component: NotificacionComponent },
  { path: 'personalizar', component: NotificacionPersonalizadaComponent },
  { path: 'listaVacantes', component: ListaVacantesComponent },
  { path: 'detalleVacante', component: DetalleVacanteComponent },
  { path: 'postulacionVacante', component: PostulacionVacanteComponent },
  { path: 'mostrarInvitacion', component: MostrarinvitacionComponent },
  { path: 'mostrarAdjA', component: MostraradjaComponent },
  { path: 'mostrarAdjR', component: MostraradjrComponent },
  { path: 'elaborarpropuesta', component: ElaborarpropuestaComponent },
  { path: 'equipos', component: EquiposComponent },
  { path: 'infraestructuras', component: InfraestructurasComponent },
  { path: 'cronogramas', component: CronogramasComponent },
  { path: 'equipos/nuevo', component: NuevoEquipoComponent },
  { path: 'equipos/:id', component: DetalleEquipoComponent },
  { path: 'infraestructuras/nuevo', component: NuevaInfraestructuraComponent },
  { path: 'infraestructuras/:id', component: DetalleInfraestructuraComponent }
];
