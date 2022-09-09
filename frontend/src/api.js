const shortenerServiceUrl = 'http://localhost:8080/api/v1'

const getShortenedUrl = async (originalUrl) => {
    const requestBody = {
        originalUrl
    }
    const response = await fetch(`${shortenerServiceUrl}/url`, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody) // body data type must match "Content-Type" header
    });

    const responseJson = await response.json()
    console.log(responseJson)
    return `http://localhost:8080/${responseJson.payload.hash}`
}

export {getShortenedUrl}