import { getLocaleNumberSymbol } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }

  items: MenuItem[] = [];

  activeItem: MenuItem = {};

  menuId = "1";


  ngOnInit() {
    this.items = [
      {
        id: "1", label: 'One', icon: 'pi pi-fw pi-question', command: () => {
          this.setMenuId("1");
        }
      },
      { id: "2", label: 'Two', icon: 'pi pi-fw pi-question' , command: () => {
        this.setMenuId("2");
      }},
      { id: "3", label: 'Three', icon: 'pi pi-fw pi-question' , command: () => {
        this.setMenuId("3");
      }},
      { id: "4", label: 'Four', icon: 'pi pi-fw pi-question' , command: () => {
        this.setMenuId("4");
      }},
      { id: "5", label: 'Five', icon: 'pi pi-fw pi-question' , command: () => {
        this.setMenuId("5");
      }}
    ];

    this.activeItem = this.items[0];
  }

  setMenuId(id: string) {
    console.log(id)
    this.menuId = id;
  }




}


