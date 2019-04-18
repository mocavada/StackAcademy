/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { EmployeeAdminServiceService } from './employee-admin-service.service';

describe('Service: EmployeeAdminService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmployeeAdminServiceService]
    });
  });

  it('should ...', inject([EmployeeAdminServiceService], (service: EmployeeAdminServiceService) => {
    expect(service).toBeTruthy();
  }));
});
