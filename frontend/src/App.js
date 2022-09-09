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

    return (
        <main>
            <nav>
                <h1 id="navbar-logo">Url Shortener</h1>
            </nav>

            <div>
                <div className="flex-center space-x-2">
                    <input id="url-input" type="text" value={originalUrl} onChange={onOriginalUrlChange}/>
                    <button id="url-input-submit" onClick={onClick}>Shorten!</button>
                </div>
                {shortenedUrl &&
                    <div className="flex-center mt-4">
                        <p id="shortened-url">{shortenedUrl}</p>
                    </div>
                }
            </div>
        </main>
    );
}

export default App;
