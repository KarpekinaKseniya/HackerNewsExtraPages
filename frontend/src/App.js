import './App.css';
import React from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import StoriesList from "./component/StoriesList";

function App() {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<StoriesList/>}/>
                {/*<Route path="/:id" element={<Card />} />*/}
                {/*<Route path="*" element={<ErrorPage />} />*/}
            </Routes>
        </Router>
    );
}

export default App;
