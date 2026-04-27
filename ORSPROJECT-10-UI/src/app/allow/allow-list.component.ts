import { Component } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-allow-list',
  templateUrl: './allow-list.component.html',
  styles: [
  ]
})
export class AllowListComponent extends BaseListCtl {


  constructor(public locator: ServiceLocatorService, route: ActivatedRoute) {
    super(locator.endpoints.ALLOW, locator, route);
  }

}
