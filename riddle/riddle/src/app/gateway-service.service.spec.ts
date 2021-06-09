import { TestBed } from '@angular/core/testing';

import { GatewayServiceService } from './gateway-service.service';

describe('GatewayServiceService', () => {
  let service: GatewayServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GatewayServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
