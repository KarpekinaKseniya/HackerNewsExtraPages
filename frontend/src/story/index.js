import {getComment, getStories, getStory} from "../service/BackendRequest";

export function createStoriesFunction() {

    return {

        stories: [],
        story: {},
        isLoading: false,
        comments: [],
        treeComments: [],

        fetchStories() {
            this.isLoading = true;
            try {
                getStories().then((json) => {
                    this.stories = json;
                    this.isLoading = false;
                });
            } catch (err) {
                console.log(err.message);
            }
        },

        fetchStory(id) {
            try {
                getStory(id).then((json) => {
                    this.story = json;
                });
            } catch (err) {
                console.log(err.message);
            }
        },

        async fetchComments(IDs) {
            this.comments = await Promise.all(
                IDs.map(
                    async (id) =>
                        await getComment(id).then((json) => {
                            return json;
                        })
                )
            );
        },

        async fetchTreeComments(IDs) {
            this.treeComments = await Promise.all(
                IDs.map(
                    async (id) =>
                        await getComment(id).then((json) => {
                            return json;
                        })
                )
            );
        }

    };

}