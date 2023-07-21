import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PageLoginComponent} from './pages/page-login/page-login.component';
import {PageInscriptionComponent} from './pages/page-inscription/page-inscription.component';
import { PageArticleComponent } from './pages/articles/page-article/page-article.component';
import {PageDashboardComponent} from './pages/page-dashboard/page-dashboard.component';
import {PageStatistiquesComponent} from './pages/page-statistiques/page-statistiques.component';
import { PageMvtstkComponent } from './pages/mvtstk/page-mvtstk/page-mvtstk.component';
import { NouveauCltFrsComponent } from './composants/nouveau-clt-frs/nouveau-clt-frs.component';
import {NouvelleCmdCltFrsComponent} from './composants/nouvelle-cmd-clt-frs/nouvelle-cmd-clt-frs.component';
import {NouvelArticleComponent} from './pages/articles/nouvel-article/nouvel-article.component';
import {PageClientComponent} from './pages/client/page-client/page-client.component';
import {PageCmdCltFrsComponent} from './pages/page-cmd-clt-frs/page-cmd-clt-frs.component';
import {PageFournisseurComponent} from './pages/fournisseur/page-fournisseur/page-fournisseur.component';
import {PageCategoriesComponent} from './pages/categories/page-categories/page-categories.component';
import {NouvelleCategoryComponent} from './pages/categories/nouvelle-category/nouvelle-category.component';
import {PageUtilisateurComponent} from './pages/utilisateur/page-utilisateur/page-utilisateur.component';
import {NouvelUtilisateurComponent} from './pages/utilisateur/nouvel-utilisateur/nouvel-utilisateur.component';
import {PageProfilComponent} from './pages/profil/page-profil/page-profil.component';
import {ChangerMotDePasseComponent} from './pages/profil/changer-mot-de-passe/changer-mot-de-passe.component';
import {GuardService} from './services/guard.service';

const routes: Routes = [
  {
    path: 'login',
    component: PageLoginComponent
  },
  {
    path: 'inscrire',
    component: PageInscriptionComponent
  },
  {
    path: '',
    component: PageDashboardComponent,
    canActivate: [GuardService],
    children: [
      {
        path: 'statistiques',
        component: PageStatistiquesComponent,
        canActivate: [GuardService],
      },
      {
        path: 'articles',
        component: PageArticleComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouvelarticle',
        component: NouvelArticleComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouvelarticle/:idArticle',
        component: NouvelArticleComponent,
        canActivate: [GuardService],
      },
      {
        path: 'mvtstk',
        component: PageMvtstkComponent,
        canActivate: [GuardService],
      },
      {
        path: 'clients',
        component: PageClientComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouveauclient',
        component: NouveauCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'nouveauclient/:id',
        component: NouveauCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'commandesclient',
        component: PageCmdCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'nouvellecommandeclt',
        component: NouvelleCmdCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'client'
        }
      },
      {
        path: 'fournisseurs',
        component: PageFournisseurComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouveaufournisseur',
        component: NouveauCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'nouveaufournisseur/:id',
        component: NouveauCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'commandesfournisseur',
        component: PageCmdCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'nouvellecommandefrs',
        component: NouvelleCmdCltFrsComponent,
        canActivate: [GuardService],
        data: {
          origin: 'fournisseur'
        }
      },
      {
        path: 'categories',
        component: PageCategoriesComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouvellecategorie',
        component: NouvelleCategoryComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouvellecategorie/:idCategory',
        component: NouvelleCategoryComponent,
        canActivate: [GuardService],
      },
      {
        path: 'utilisateurs',
        component: PageUtilisateurComponent,
        canActivate: [GuardService],
      },
      {
        path: 'nouvelutilisateur',
        component: NouvelUtilisateurComponent,
        canActivate: [GuardService],
      },
      {
        path: 'profil',
        component: PageProfilComponent,
        canActivate: [GuardService],
      },
      {
        path: 'changermotdepasse',
        component: ChangerMotDePasseComponent,
        canActivate: [GuardService],
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }