import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CltfrsService} from '../../services/cltfrs.service';
import {ClientDto} from '../../../gs-api/src/models/client-dto';
import {AdresseDto} from '../../../gs-api/src/models/adresse-dto';
import {FournisseurDto} from '../../../gs-api/src/models/fournisseur-dto';
import {PhotosService} from '../../../gs-api/src/services/photos.service';
import SavePhotoParams = PhotosService.SavePhotoParams;

@Component({
  selector: 'app-nouveau-clt-frs',
  templateUrl: './nouveau-clt-frs.component.html',
  styleUrls: ['./nouveau-clt-frs.component.scss']
})
export class NouveauCltFrsComponent implements OnInit {
    origin = '';
    clientFournisseur: ClientDto = {};
    adresseDto: AdresseDto = {};
    errorMsg: Array<ClientDto> = [];
    imgUrl: string | ArrayBuffer = 'assets/product.png';
    file: File | null = null;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private cltFrsService: CltfrsService,
              private photoService: PhotosService) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data.origin;
    });
    this.findObject();
  }

  enregistrerClFr(): void {
    if (this.origin === 'client') {
      this.cltFrsService.enregistrerClt(this.mapToClient())
          .subscribe(client => {
              this.savePhoto(client.id, client.nom);
          }, error => {
            this.errorMsg = error.error.errors;
          });
    }else if (this.origin === 'fournisseur') {
      this.cltFrsService.enregistrerFrs(this.mapToFournisseur())
          .subscribe(fournisseur => {
              this.savePhoto(fournisseur.id, fournisseur.nom);
          }, error => {
            this.errorMsg = error.error.errors;
          });
    }
  }

    findObject(): void {
        const id = this.activatedRoute.snapshot.params.id;
        if (id) {
            if (this.origin === 'client') {
                this.cltFrsService.findCltById(id)
                .subscribe(client => {
                    this.clientFournisseur = client;
                    if (this.clientFournisseur.adresse) {
                        this.adresseDto = this.clientFournisseur.adresse;
                    }
                });
            } else if (this.origin === 'fournisseur') {
                this.cltFrsService.findFrsById(id)
                    .subscribe(fournisseur => {
                        this.clientFournisseur = fournisseur;
                        if (this.clientFournisseur.adresse) {
                            this.adresseDto = this.clientFournisseur.adresse;
                        }
                    });
            }
        }
    }

  cancelClick(): void{
    if (this.origin === 'client') {
      this.router.navigate(['clients']);
    } else if (this.origin === 'fournisseur') {
      this.router.navigate(['fournisseurs']);
    }
  }

  mapToClient(): ClientDto{
    const clientDto: ClientDto = this.clientFournisseur;
    clientDto.adresse = this.adresseDto;
    return clientDto;
  }

    mapToFournisseur(): FournisseurDto{
        const forunisseurDto: FournisseurDto = this.clientFournisseur;
        forunisseurDto.adresse = this.adresseDto;
        return forunisseurDto;
    }

    // Cette méthode permet d'aploader une photo dans un formulaire
    onFileInput(files: FileList | null): void {
        if (files){
            this.file = files.item(0);
            if (this.file){
                const fileReader = new FileReader();
                fileReader.readAsDataURL(this.file);
                fileReader.onload = (event) => {
                    if (fileReader.result) {
                        this.imgUrl = fileReader.result;
                    }
                };
            }
        }
    }
    // Cette permet d'enregistrer une photo via le service photo généré.
    savePhoto(idCltFrs?: number, title?: string): void{
        if (idCltFrs && title && this.file) {
            const params: SavePhotoParams = {
                id: idCltFrs,
                file: this.file,
                titre: title,
                context: this.origin
            };
            this.photoService.savePhoto(params)
                .subscribe(photo => {
                    this.cancelClick();
                });
        } else {
            this.cancelClick();        }
    }
}
