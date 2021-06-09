import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ButtonModule } from 'primeng/button';
import { TabMenuModule } from 'primeng/tabmenu';
import { PanelModule } from 'primeng/panel';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AccordionModule } from 'primeng/accordion';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { StartComponent } from './start/start.component';
import { MenuComponent } from './menu/menu.component';
import { RiddleComponent } from './riddle/riddle.component';
import { GatewayServiceService } from './gateway-service.service';

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    MenuComponent,
    RiddleComponent
  ],
  imports: [
    BrowserModule,
    ButtonModule,
    TabMenuModule,
    PanelModule,
    BrowserAnimationsModule,
    AccordionModule,
    CardModule,
    InputTextModule,
    InputTextareaModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [GatewayServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
