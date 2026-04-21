import { Component } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-prescription-list',
  templateUrl: './prescription-list.component.html',
  styles: [
  ]
})
export class PrescriptionListComponent extends BaseListCtl {

  constructor(public locator:ServiceLocatorService, route:ActivatedRoute){
      super(locator.endpoints.PRESCRIPTION,locator,route);
    }

}
