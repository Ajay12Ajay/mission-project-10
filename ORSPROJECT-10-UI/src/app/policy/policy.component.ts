import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceLocatorService } from '../service-locator.service';
import { BaseCtl } from '../base.component';


@Component({
  selector: 'app-policy',
  templateUrl: './policy.component.html',
  styles: [
  ]
})
export class PolicyComponent extends BaseCtl {

  constructor(public locator:ServiceLocatorService, route:ActivatedRoute){
    super(locator.endpoints.POLICY,locator,route);
  }

}
