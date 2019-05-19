import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface Selction {
  value: string;
  viewValue: string;
}


export interface FieldInformation {
  name: string;
  type: string;
}

export interface ClassInfromation {
  className: string;
  fields: FieldInformation[];
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  selectedCategory: Selction;
  selectedModel: ClassInfromation;

  constructor(private httpClient: HttpClient) {
  }

  categories: Selction[] = [
    {value: 'battery', viewValue: 'Battery'},
    {value: 'solarPanel', viewValue: 'Solar Panel'}
  ];

  modles: ClassInfromation[] = [];


  displayedColumns: string[] = ['name', 'type'];
  fields: FieldInformation[] = [];

  loadModels() {
    if (this.selectedCategory.value === 'battery') {
      this.loadBatteries();
    }

    if (this.selectedCategory.value === 'solarPanel') {
      this.loadSolarpanels();
    }
  }

  loadFields() {
    this.fields = this.selectedModel ? this.selectedModel.fields : [];
  }

  loadBatteries() {
    this.httpClient.get<ClassInfromation[]>('http://localhost:8080/batteries').subscribe((data: ClassInfromation[]) => {
        this.modles = data;
    });
  }

  loadSolarpanels() {
    this.httpClient.get<ClassInfromation[]>('http://localhost:8080/solarPanels').subscribe((data: ClassInfromation[]) => {
        this.modles = data;
    });
  }

}
