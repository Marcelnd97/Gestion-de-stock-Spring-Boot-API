/* tslint:disable */
import { NgModule, ModuleWithProviders } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationInterface } from './api-configuration';

import { ArticlesService } from './services/articles.service';
import { AuthenticationService } from './services/authentication.service';
import { CategoryService } from './services/category.service';
import { ClientService } from './services/client.service';
import { CommandeClientsService } from './services/commande-clients.service';
import { CommandefournisseurService } from './services/commandefournisseur.service';
import { EntrepriseService } from './services/entreprise.service';
import { FournisseurService } from './services/fournisseur.service';
import { MouveStockService } from './services/mouve-stock.service';
import { PhotosService } from './services/photos.service';
import { UtilisateurService } from './services/utilisateur.service';
import { VenteService } from './services/vente.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    ArticlesService,
    AuthenticationService,
    CategoryService,
    ClientService,
    CommandeClientsService,
    CommandefournisseurService,
    EntrepriseService,
    FournisseurService,
    MouveStockService,
    PhotosService,
    UtilisateurService,
    VenteService
  ],
})
export class ApiModule {
  static forRoot(customParams: ApiConfigurationInterface): ModuleWithProviders {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: {rootUrl: customParams.rootUrl}
        }
      ]
    }
  }
}
