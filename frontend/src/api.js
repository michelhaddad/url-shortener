const shortenerServiceHost = 'http://localhost:9001'
const shortenerServiceUrlEndpoint = '/api/v1/url'

const getShortenedUrl = async (originalUrl) => {
    const requestBody = {
        originalUrl
    }
    const response = await fetch(`${shortenerServiceHost}${shortenerServiceUrlEndpoint}`, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'X-Requested-With',
        },
        body: JSON.stringify(requestBody)
    });

    const responseJson = await response.json()
    return `${shortenerServiceHost}/${responseJson.payload.hash}`
}

export {
    getShortenedUrl
}