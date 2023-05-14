import './App.css';
import React from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import StoriesList from "./component/StoriesList";
import Error from "./component/Error";
import StoryInfo from "./component/StoryInfo";

function App() {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<StoriesList/>}/>
                <Route path="/:id" element={<StoryInfo />} />
                <Route path="*" element={<Error/>}/>
            </Routes>
        </Router>
    );
}

export default App;
