import { Component } from '@angular/core';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpServiceService } from '../http-service.service';

@Component({
  selector: 'app-getmarksheet',
  templateUrl: './getmarksheet.component.html',
  styles: [
  ]
})
export class GetmarksheetComponent extends BaseCtl {

   constructor(public locator: ServiceLocatorService, route: ActivatedRoute, private httpservice: HttpServiceService) {
    super(locator.endpoints.MARKSHEET, locator, route);

  }

  go() {
    var _self = this;
    console.log("onClickSubmit");
    console.log(this.form.data);
    console.log(this.form.data.rollNo);


    this.httpservice.get("http://localhost:8080/Marksheet/rollno/" + this.form.data.rollNo, function (res: any) {

      if (res.success) {
        _self.form.list = res.result.data;
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
