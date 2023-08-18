import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NouvelArticleComponent } from './pages/articles/nouvel-article/nouvel-article.component';
import { NouvelleCategoryComponent } from './pages/categories/nouvelle-category/nouvelle-category.component';
import { PageArticleComponent } from './pages/articles/page-article/page-article.component';
import { PageCategoriesComponent } from './pages/categories/page-categories/page-categories.component';
import { PageClientComponent } from './pages/client/page-client/page-client.component';
import { PageFournisseurComponent } from './pages/fournisseur/page-fournisseur/page-fournisseur.component';
import { PageMvtstkComponent } from './pages/mvtstk/page-mvtstk/page-mvtstk.component';
import { PageCmdCltFrsComponent } from './pages/page-cmd-clt-frs/page-cmd-clt-frs.component';
import { PageDashboardComponent } from './pages/page-dashboard/page-dashboard.component';
import { PageInscriptionComponent } from './pages/page-inscription/page-inscription.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageStatistiquesComponent } from './pages/page-statistiques/page-statistiques.component';
import { ButtonActionComponent } from './composants/button-action/button-action.component';
import { DetailArticleComponent } from './composants/detail-article/detail-article.component';
import { DetailCltFrsComponent } from './composants/detail-clt-frs/detail-clt-frs.component';
import { DetailCmdComponent } from './composants/detail-cmd/detail-cmd.component';
import { DetailCmdCltFrsComponent } from './composants/detail-cmd-clt-frs/detail-cmd-clt-frs.component';
import { DetailMvtStkComponent } from './composants/detail-mvt-stk/detail-mvt-stk.component';
import { DetailMvtStkArticleComponent } from './composants/detail-mvt-stk-article/detail-mvt-stk-article.component';
import { DetailUtilisateurComponent } from './composants/detail-utilisateur/detail-utilisateur.component';
import { PaginationComponent } from './composants/pagination/pagination.component';
import { HeaderComponent } from './composants/header/header.component';
import { MenuComponent } from './composants/menu/menu.component';
import { NouveauCltFrsComponent } from './composants/nouveau-clt-frs/nouveau-clt-frs.component';
import { NouvelleCmdCltFrsComponent } from './composants/nouvelle-cmd-clt-frs/nouvelle-cmd-clt-frs.component';
import { PageUtilisateurComponent } from './pages/utilisateur/page-utilisateur/page-utilisateur.component';
import { NouvelUtilisateurComponent } from './pages/utilisateur/nouvel-utilisateur/nouvel-utilisateur.component';
import { PageProfilComponent } from './pages/profil/page-profil/page-profil.component';
import { ChangerMotDePasseComponent } from './pages/profil/changer-mot-de-passe/changer-mot-de-passe.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {InterceptorService} from './services/interceptor.service';
import {NgxUiLoaderHttpModule, NgxUiLoaderModule} from 'ngx-ui-loader';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DonneeArticleComponent } from './composants/donnee-article/donnee-article.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';


@NgModule({
  declarations: [
    AppComponent,
    NouvelArticleComponent,
    NouvelleCategoryComponent,
    PageArticleComponent,
    PageCategoriesComponent,
    PageClientComponent,
    PageFournisseurComponent,
    PageMvtstkComponent,
    PageCmdCltFrsComponent,
    PageDashboardComponent,
    PageInscriptionComponent,
    PageLoginComponent,
    PageStatistiquesComponent,
    ButtonActionComponent,
    DetailArticleComponent,
    DetailCltFrsComponent,
    DetailCmdComponent,
    DetailCmdCltFrsComponent,
    DetailMvtStkComponent,
    DetailMvtStkArticleComponent,
    DetailUtilisateurComponent,
    PaginationComponent,
    HeaderComponent,
    MenuComponent,
    NouveauCltFrsComponent,
    NouvelleCmdCltFrsComponent,
    PageUtilisateurComponent,
    NouvelUtilisateurComponent,
    PageProfilComponent,
    ChangerMotDePasseComponent,
    DonneeArticleComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        NgxUiLoaderModule,
        NgxUiLoaderHttpModule.forRoot({
            showForeground: true,
        }),
        BrowserAnimationsModule,
        MatMenuModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatDividerModule,
    ],
  providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
