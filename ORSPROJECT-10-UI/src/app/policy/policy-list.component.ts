import { Component } from '@angular/core';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { BaseListCtl } from '../base-list.component';

@Component({
  selector: 'app-policy-list',
  templateUrl: './policy-list.component.html',
  styles: [
  ]
})
export class PolicyListComponent extends BaseListCtl {

  constructor(public locator:ServiceLocatorService, route:ActivatedRoute){
    super(locator.endpoints.POLICY,locator,route);
  }

}
