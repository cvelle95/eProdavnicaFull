import { NgModule } from '@angular/core';
import {Routes , RouterModule} from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ItemComponent } from './shop/item/item.component';
import { ProfileContainerComponent } from './user/profile-container/profile-container.component';
import { CartComponent } from './shop/cart/cart.component';
import {StatsComponent} from './stats/stats.component';

const rute: Routes = [
    {path: '', component: WelcomeComponent},
    {path: 'signup', component: SignupComponent},
    {path: 'login', component: LoginComponent},
    {path: 'item' , component: ItemComponent},
    {path: 'profile' , component: ProfileContainerComponent },
    {path: 'cart' , component: CartComponent },
    {path: 'stats' , component: StatsComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(rute,{ onSameUrlNavigation: 'reload' })
    ],
    exports: [
        RouterModule
    ]

})

export class RoutingModule {}
