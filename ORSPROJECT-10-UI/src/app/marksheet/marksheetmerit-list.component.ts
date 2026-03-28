import { Component } from '@angular/core';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpServiceService } from '../http-service.service';

@Component({
  selector: 'app-marksheetmerit-list',
  templateUrl: './marksheetmerit-list.component.html',
  styles: [
  ]
})
export class MarksheetmeritListComponent extends BaseCtl {

  constructor(public locator: ServiceLocatorService, route: ActivatedRoute, private httpservice: HttpServiceService) {
    super(locator.endpoints.MARKSHEET, locator, route);

  }

  override ngOnInit() {
    this.getMeritList();
  }

  getMeritList() {
    var _self = this;

    this.httpservice.get("http://localhost:8080/Marksheet/meritlist", function (res: any) {

      if (res.success) {
        _self.form.list = res.result.list;
        if (_self.form.list.length == 0) {
          _self.form.message = "No record found";
          _self.form.error = true;
        }
        console.log("List Size", _self.form.list.length);
      } else {
        _self.form.error = false;
        _self.form.message = res.result.message;
      }
      console.log('FORM', _self.form);
    });
  }


}
