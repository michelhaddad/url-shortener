import {useState} from "react";
import {getShortenedUrl} from "./api";

function App() {
    const [originalUrl, setOriginalUrl] = useState('')
    const [shortenedUrl, setShortenedUrl] = useState('')

    const onOriginalUrlChange = event => {
        setOriginalUrl(event.target.value)
    }

    const onClick = async () => {
        const shortenedUrl = await getShortenedUrl(originalUrl)
        setShortenedUrl(shortenedUrl)
    }

    const copyToClipboardGenerator = (text) => async () => {
        await navigator.clipboard.writeText(text)
    }

    return (
        <>
            <div className="curve"></div>
            <main>
                <div className="div-title">
                    <h1>URL Shortener</h1>
                    <p>Shorter. Simpler. Better.</p>
                    <div className="input-container space-x-2">
                        <div className="custom-input">
                            <input onChange={onOriginalUrlChange} type="text" placeholder="Type your URL here!"/>
                        </div>
                        <button onClick={onClick} id="submit-button">Try it out!</button>
                    </div>

                    {
                        shortenedUrl &&
                        <div id="div-short-url" className="space-x-4">
                            <p>{shortenedUrl}</p>
                            <button onClick={copyToClipboardGenerator(shortenedUrl)}>Copy</button>
                        </div>
                    }
                </div>
            </main>
        </>
    );
}

export default App;
