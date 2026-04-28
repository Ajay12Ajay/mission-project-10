import { Component } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-transport-list',
  templateUrl: './transport-list.component.html',
  styles: [
  ]
})
export class TransportListComponent extends BaseListCtl {


  constructor(public locator: ServiceLocatorService, route: ActivatedRoute) {
    super(locator.endpoints.TRANSPORT, locator, route)
  }

}
