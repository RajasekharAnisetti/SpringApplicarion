/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ShippingAddressService } from 'app/entities/shipping-address/shipping-address.service';
import { IShippingAddress, ShippingAddress } from 'app/shared/model/shipping-address.model';

describe('Service Tests', () => {
    describe('ShippingAddress Service', () => {
        let injector: TestBed;
        let service: ShippingAddressService;
        let httpMock: HttpTestingController;
        let elemDefault: IShippingAddress;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ShippingAddressService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ShippingAddress(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                false,
                false,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ShippingAddress', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ShippingAddress(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ShippingAddress', async () => {
                const returnedFromService = Object.assign(
                    {
                        attention: 'BBBBBB',
                        company: 'BBBBBB',
                        address1: 'BBBBBB',
                        address2: 'BBBBBB',
                        phone: 'BBBBBB',
                        email: 'BBBBBB',
                        postalCode: 'BBBBBB',
                        confirmDelivery: true,
                        instructions: 'BBBBBB',
                        notifyRecipient: true,
                        res: true,
                        tailgate: true
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ShippingAddress', async () => {
                const returnedFromService = Object.assign(
                    {
                        attention: 'BBBBBB',
                        company: 'BBBBBB',
                        address1: 'BBBBBB',
                        address2: 'BBBBBB',
                        phone: 'BBBBBB',
                        email: 'BBBBBB',
                        postalCode: 'BBBBBB',
                        confirmDelivery: true,
                        instructions: 'BBBBBB',
                        notifyRecipient: true,
                        res: true,
                        tailgate: true
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ShippingAddress', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
