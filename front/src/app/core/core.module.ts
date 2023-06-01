import {LOCALE_ID, NgModule} from '@angular/core';
import {CommonModule, registerLocaleData} from '@angular/common';
import {RouterModule} from "@angular/router";
import * as fr from '@angular/common/locales/fr';
import {NavbarComponent} from "./components/navbar/navbar.component";
import {httpInterceptorProviders} from "./interceptors";

@NgModule({
  declarations: [
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'fr-FR'},
    httpInterceptorProviders
  ],
  exports: [
    NavbarComponent
  ]
})
export class CoreModule {
  constructor() {
    registerLocaleData(fr.default);
  }
}
