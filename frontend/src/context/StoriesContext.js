import React from 'react';
import {useLocalObservable} from 'mobx-react-lite';
import {createStoriesFunction} from "../story";

const StoriesContext = React.createContext(null);

export const StoriesProvider = ({children}) => {
    const storiesStore = useLocalObservable(createStoriesFunction);

    return <StoriesContext.Provider value={storiesStore}>{children}</StoriesContext.Provider>;
};

export const useStoriesStore = () => React.useContext(StoriesContext);