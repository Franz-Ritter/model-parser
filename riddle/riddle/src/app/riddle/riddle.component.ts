import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { GatewayServiceService } from '../gateway-service.service';

interface RiddleDto {
  id?: string,
  task?: string,
  riddle?: string,
  solution?: string
}

@Component({
  selector: 'app-riddle',
  templateUrl: './riddle.component.html',
  styleUrls: ['./riddle.component.css']
})
export class RiddleComponent implements OnInit, OnChanges {

  @Input() id = "";
  showSolution = true;
  showRiddle = false;

  selectedRiddle: RiddleDto = {};
  code: string = "";

  constructor(private gatewayService: GatewayServiceService) {

  }
  ngOnChanges(changes: SimpleChanges): void {
    this.loadContent();
  }

  async loadContent() {
    console.log("ridd")
    const riddle: any = await this.gatewayService.getRiddle(this.id);
    if (!!riddle.Item) {
      console.log("i have an item");
      this.selectedRiddle = {
        id: riddle.Item.number.S,
        task: riddle.Item.explanation.S,
        riddle: riddle.Item.riddle.S,
        solution: riddle.Item.solution.S
      }
      console.log(this.selectedRiddle)
    }
    console.log(riddle);
  }

  ngOnInit(): void {
  }

  isCodeGiven() {
    console.log("riddle ID = " + this.selectedRiddle.id)
    console.log("code " + this.code)
    this.showRiddle = !!this.selectedRiddle && this.selectedRiddle.id === this.code;
  }

}
