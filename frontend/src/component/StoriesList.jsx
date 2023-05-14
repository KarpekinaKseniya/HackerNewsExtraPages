import * as React from 'react';
import {useEffect} from 'react';

import {observer} from 'mobx-react-lite';
import {useStoriesStore} from "../context/StoriesContext";
import LinearProgress from '@mui/material/LinearProgress';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {Button, Grid, TableHead} from "@mui/material";

const columns = [
    {id: 'title', label: 'Title', align: 'left', minWidth: 250},
    {id: 'author', label: 'Author', align: 'left', minWidth: 170},
    {id: 'score', label: 'Score', align: 'left', minWidth: 100},
    {id: 'time', label: 'Created Date & Time', align: 'left', minWidth: 150},
];

const StoriesList = observer(() => {

    const story = useStoriesStore();
    useEffect(() => {
        setTimeout(function () {
            story.fetchStories();
        }, 60000);
        story.fetchStories();
    }, []);

    const reLoadPage = () => {
        story.fetchStories();
    };

    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    return (
        <div className="wrapper">
            <Grid container direction="row" alignItems="center">
                <Grid item xs={10}>
                    <h3>Hacker News</h3>
                </Grid>
                <Grid item xs={2}>
                    <Button onClick={() => reLoadPage()}>
                        Reload this Page
                    </Button>
                </Grid>
            </Grid>
            <div className="hacker-news">
                {story.isLoading ? (
                    <LinearProgress/>
                ) : (
                    <Paper>
                        <TableContainer>
                            <Table stickyHeader aria-label="sticky table">
                                <TableHead>
                                    <TableRow>
                                        {columns.map((column) => (
                                            <TableCell
                                                key={column.id}
                                                align={column.align}
                                                style={{minWidth: column.minWidth}}>
                                                {column.label}
                                            </TableCell>
                                        ))}
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {story.stories
                                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                        .map((row) => {
                                            return (
                                                <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                                                    {columns.map((column) => {
                                                        const value = row[column.id];
                                                        return (
                                                            <TableCell key={column.id} align={column.align}>
                                                                {column.format && typeof value === 'number'
                                                                    ? column.format(value)
                                                                    : value}
                                                            </TableCell>
                                                        );
                                                    })}
                                                </TableRow>
                                            );
                                        })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <TablePagination
                            rowsPerPageOptions={[10, 25, 50, 100]}
                            component="div"
                            count={story.stories.length}
                            rowsPerPage={rowsPerPage}
                            page={page}
                            onPageChange={handleChangePage}
                            onRowsPerPageChange={handleChangeRowsPerPage}
                        />
                    </Paper>
                )}
            </div>
        </div>
    );
});

export default StoriesList;