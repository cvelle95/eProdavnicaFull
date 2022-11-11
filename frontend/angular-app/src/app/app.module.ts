import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { MaterialModule } from './material.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { RoutingModule } from './routing.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule } from '@angular/forms';
import { SaleComponent } from './shop/sale/sale.component';
import { ItemComponent } from './shop/item/item.component';
import { ProfileComponent } from './user/profile/profile.component';
import { OrdersComponent } from './user/orders/orders.component';
import { ProfileContainerComponent } from './user/profile-container/profile-container.component';
import { UserService } from './_services/user.service';
import { ItemService } from './_services/item.service';
import { OrderService } from './_services/order.service';
import { HttpClientModule } from '@angular/common/http';
import { CartComponent } from './shop/cart/cart.component';
import { FooterComponent } from './footer/footer/footer.component';
import { DialogComponent } from './user/dialog/dialog.component';
import { StatsComponent } from './stats/stats.component';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    LoginComponent,
    WelcomeComponent,
    SaleComponent,
    ItemComponent,
    ProfileComponent,
    OrdersComponent,
    ProfileContainerComponent,
    CartComponent,
    FooterComponent,
    DialogComponent,
    StatsComponent
  ],
    imports: [
        BrowserModule,
        MaterialModule,
        RoutingModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
        FormsModule,
        HttpClientModule,
        MatTableModule
    ],
  providers: [UserService,ItemService,OrderService,HttpClientModule],
  bootstrap: [AppComponent],
  entryComponents:[ProfileComponent,DialogComponent]
})
export class AppModule { }
