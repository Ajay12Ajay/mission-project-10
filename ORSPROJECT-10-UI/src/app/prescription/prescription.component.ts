import { Component } from '@angular/core';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-prescription',
  templateUrl: './prescription.component.html',
  styles: [
  ]
})
export class PrescriptionComponent extends BaseCtl {

  constructor(public locator:ServiceLocatorService, route:ActivatedRoute){
    super(locator.endpoints.PRESCRIPTION,locator,route);
  }

}
