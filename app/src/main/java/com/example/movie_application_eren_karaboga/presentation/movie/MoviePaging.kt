import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie_application_eren_karaboga.data.models.Movie
import com.example.movie_application_eren_karaboga.data.remote.service.MovieInterface
import com.example.movie_application_eren_karaboga.base.utils.Constants

class MoviePaging(val language: String, val movieInterface: MovieInterface) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val data = movieInterface.getAllMovies(Constants.BASE_URL, language, page)
            LoadResult.Page(
                data = data.body()?.results!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.results!!.isEmpty()) null else page + 1
            )

        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}