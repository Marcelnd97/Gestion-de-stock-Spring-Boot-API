import { Component, OnInit } from '@angular/core';
import {EntrepriseDto} from '../../../gs-api/src/models/entreprise-dto';
import {AdresseDto} from '../../../gs-api/src/models/adresse-dto';
import {EntreprisesService} from '../../services/entreprises.service';
import {AuthenticationRequest} from '../../../gs-api/src/models/authentication-request';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {PhotosService} from '../../../gs-api/src/services/photos.service';
import SavePhotoParams = PhotosService.SavePhotoParams;

@Component({
  selector: 'app-page-inscription',
  templateUrl: './page-inscription.component.html',
  styleUrls: ['./page-inscription.component.scss']
})
export class PageInscriptionComponent implements OnInit {
  entrepriseDto: EntrepriseDto = {};
  adresse: AdresseDto = {};
  errorMsg: Array<string> = [];
  file: File | null = null;
  imgUrl: string | ArrayBuffer = 'assets/product.png';

  constructor(private entreprisesService: EntreprisesService,
              private userService: UserService,
              private router: Router,
              private photoService: PhotosService) { }

  ngOnInit(): void {
  }

  inscrire(): void {
    this.entrepriseDto.adresse = this.adresse;
    this.entreprisesService.sinscrire(this.entrepriseDto)
      .subscribe(entrepriseDto => {
      this.savePhoto(entrepriseDto.id, entrepriseDto.nom);
      this.connectEntreprise();
    }, error => {
      this.errorMsg = error.error.errors;
    });
  }
  connectEntreprise(): void{
    const authenticationRequest: AuthenticationRequest = {
      login: this.entrepriseDto.email,
      password: 'som3R@nd0mP@$$word'
    };
    this.userService.login(authenticationRequest)
        .subscribe(response => {
          this.userService.setAccessToken(response);
          this.getUserByEmail(authenticationRequest.login);
          localStorage.setItem('origin', 'inscription');
          this.router.navigate(['changermotdepasse']);
        });
  }

  getUserByEmail(email?: string): void{
    this.userService.getUserByEmail(email).subscribe(user => {
      this.userService.setConnectedUser(user);
    });
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
  savePhoto(idEntreprise?: number, title?: string): void{
    if (idEntreprise && title && this.file) {
      const params: SavePhotoParams = {
        id: idEntreprise,
        file: this.file,
        titre: title,
        context: 'entreprise'
      };
      this.photoService.savePhoto(params)
          .subscribe(photo => {
          });
    }
  }
}
