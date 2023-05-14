import React, {useEffect} from 'react';
import {useParams} from 'react-router-dom';
import {observer} from 'mobx-react-lite';
import {useStoriesStore} from '../context/StoriesContext';
import {Button, Card, CardActions, CardContent, CardHeader, Typography} from "@mui/material";
import RedoIcon from '@mui/icons-material/Redo';

const StoryInfo = observer(() => {
    const {id} = useParams();
    const store = useStoriesStore();
    useEffect(() => {
        setTimeout(function () {
            store.fetchStory(id);
        }, 60000);
        store.fetchStory(id);
    }, []);

    return (
        <Card>
            <CardActions>
                <a href="/">
                    <Button><RedoIcon/></Button>
                </a>
            </CardActions>
            <CardHeader title={store.story.title}
                        subheader={'Created by ' + store.story.author + ' Created at ' + store.story.time}/>
            <CardContent>
                <Typography gutterBottom variant="text" component="div">
                    <a href={store.story.url}>
                        {store.story.url}
                    </a>
                </Typography>
                <Typography variant="text" component="div">
                    Total comment count: {store.story.descendants}
                </Typography>
            </CardContent>
            <CardActions>
                <Button>Reload Comments</Button>
            </CardActions>
        </Card>
    );
});
export default StoryInfo;