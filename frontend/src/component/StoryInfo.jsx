import React, {useEffect} from 'react';
import {useParams} from 'react-router-dom';
import {observer} from 'mobx-react-lite';
import {useStoriesStore} from '../context/StoriesContext';
import {Button, Card, CardActions, CardContent, CardHeader, Divider, Grid, List, Typography} from "@mui/material";
import RedoIcon from '@mui/icons-material/Redo';
import {LowPriority} from "@mui/icons-material";

const StoryInfo = observer(() => {
    const {id} = useParams();
    const store = useStoriesStore();
    useEffect(() => {
        reloadComments();
        setTimeout(function () {
            store.fetchStory(id);
        }, 60000);
        store.fetchStory(id);
    }, []);

    const reloadComments = () => {
        if (store.story.commentIds)
            store.fetchComments(store.story.commentIds);
    };

    const commentsTree = (ids) => {
        if (ids)
            store.fetchTreeComments(ids);
    };

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
                <Typography variant="h5" component="div">
                    Comments:
                </Typography>
                {store.comments.length > 0 ? (
                    store.comments.map((comment) => (
                        <List
                            onClick={() => commentsTree(comment.kids)}
                            key={comment.id}
                            className="comment">
                            <Grid container direction="row" alignItems="center">
                                <Grid item xs={1}>
                                    {comment.kids > 0 ? <LowPriority fontSize={'small'}/> : ''}
                                </Grid>
                                <Grid item xs={11}>
                                    <p>Created by {comment.author}</p>
                                </Grid>
                            </Grid>
                            <p>{comment.text}</p>
                            <List sx={{pl: 4}}>
                                {store.treeComments &&
                                store.treeComments.map(
                                    (item) =>
                                        item.parent === comment.id && (
                                            <>
                                                <p className="author"> Created by {item.author}</p>
                                                <p key={item.id}>{item.text}</p>
                                            </>
                                        )
                                )}
                            </List>
                            <Divider/>
                        </List>
                    ))
                ) : (
                    <div className="no_comments">
                        <p>There are no comments yet</p>
                    </div>
                )}
            </CardContent>
            <CardActions>
                <Button onClick={() => {
                    reloadComments()
                }}>Reload Comments</Button>
            </CardActions>
        </Card>
    );
});
export default StoryInfo;