import http from 'k6/http';
import { check } from 'k6';

export let options = {
    vus: 5,
    duration: '5s',
    thresholds: {
        http_req_duration: ['p(95)<200'],
    },
};


const baseUrl = 'https://dev-vna-api-gami.wiinvent.tv';

// Read tokens from token.json file
const tokens = JSON.parse(open('token.json'));

function getRandomCoordinate(min, max) {
    return Math.random() * (max - min) + min;
}



function getRandomNumber(){
    const min = 10000000000; // Minimum 11-digit number
    const max = 99999999999; // Maximum 11-digit number
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

export default function () {
    const randomToken = tokens[Math.floor(Math.random() * tokens.length)];

    const latitude = getRandomCoordinate(8.18, 23.39);
    const longitude = getRandomCoordinate(102.14, 109.46);

    const contacts =[]
    for(let i = 0 ; i < 150 ; i++){
        var randomNumber = getRandomNumber();
        var string = randomNumber.toString();
        const item = {
            name: string,
            msisdn: randomNumber
        };
        contacts.push(item)
    }
    const body = JSON.stringify(contacts)

    const apiEndpoints = {
        method: 'POST',
        url: `${baseUrl}/v1/gami/it/sync/contacts`,
        // payload: {
        //     // latitude: latitude,
        //     // longitude: longitude,
        // },
        payload : {contacts},
        headers: {
            'Content-Type': 'application/json',
            // Authorization: `Bearer ${randomToken.token}`,
            Authorization: `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJnYW1pVXNlcklkIjoiMDA2ZjM3MDMtMjk3NS00ZDI2LTgyZTItY2Y4OTJlOTZkZDE3IiwiZGlzcGxheU5hbWUiOiJkaXNwbGF5X25hbWVfMTQyIiwiY291bnRyeUNvZGUiOiJWTiIsIm1zaXNkbiI6IjA5Nzk4NzEyMzE0MiIsImV4cCI6MTY5ODY1MjIzOCwidXNlcklkIjoiQlNWX0VYVDE0MiIsImN1cnJlbnRUaWVyIjoiRGVmYXVsdCIsImVtYWlsIjoiZW1haWxfMTQyQGdtYWlsLmNvbSJ9.cCOwqBv0bBpgf3WFnT9p4JRVfHriCtmGHlidBGI2i4quoksI-Z8JsaXsN8fDCKIRW9O-idhsVsIrrDElAK7FDRQuICA_10G2GVq8Lxn29jSFLk4JKZ6uiG5_IyCqHt_tsvL7roQ9dZRmpb1BRoPptEEsSnJpVSvqILk2RZtcwGI`,
        },
    };

    const res = http.request(apiEndpoints.method, apiEndpoints.url, JSON.stringify(apiEndpoints.payload), {
        headers: apiEndpoints.headers,
    });
    check(res, {
        'is status 200': (r) => r.status === 200,
    });

    if (res.status !== 200) {
        console.error(`Request to ${apiEndpoints.url} with username ${randomToken.email} failed with status code ${res.status}: ${JSON.parse(res.body).message}`);
    } else {
        console.log(`${randomToken.email} success ${res.body}`);
    }
}