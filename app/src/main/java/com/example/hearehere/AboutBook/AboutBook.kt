package com.example.hearehere.AboutBook

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.hearehere.AboutBook.AdapterAboutBook.AboutBooksAdapter
import com.example.hearehere.AboutBook.AdapterAboutBook.HeaderAboutBook
import com.example.hearehere.AboutBook.AdapterAboutBook.StaggeredGridAdapter
import com.example.hearehere.AboutBook.viewmodel.AbouBookDataViewModel
import com.example.hearehere.models.AboutBookData
import com.example.hearehere.R
import com.example.hearehere.databinding.FragmentAboutBookBinding


class AboutBook : Fragment() {
    private var _binding: FragmentAboutBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AbouBookDataViewModel by lazy {
        ViewModelProvider(this)[AbouBookDataViewModel::class.java]
    }
    private var isExpanded = false
    private lateinit var staggeredGridAdapter: StaggeredGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_book, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow)
            setDisplayShowTitleEnabled(false) //hide my proj name
        }
        setHasOptionsMenu(true)




        val dummyData: MutableList<AboutBookData> = mutableListOf(
            AboutBookData(
                data = AboutBookData.Data(
                    authorName = "Author 1",
                    categories = listOf(
                        AboutBookData.Data.Category(id = 1, title = "Category 1"),
                        AboutBookData.Data.Category(id = 2, title = "Category 2")
                    ),
                    createdAt = "2023-01-01",
                    description = "Description 1",
                    fileLink = "https://example.com/book1.pdf",
                    id = 1,
                    thumbnail_Link ="data:image/jpeg;base64,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",
                    title = "Book 1",
                    updatedAt = "2023-01-02"
                )
            ),
            AboutBookData(
                data = AboutBookData.Data(
                    authorName = "Author 2",
                    categories = listOf(
                        AboutBookData.Data.Category(id = 3, title = "Category 3"),
                        AboutBookData.Data.Category(id = 4, title = "Category 4")
                    ),
                    createdAt = "2023-02-01",
                    description = "Description 2",
                    fileLink = "https://example.com/book2.pdf",
                    id = 2,
                    thumbnail_Link ="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQA0wMBIgACEQEDEQH/xAAbAAACAwEBAQAAAAAAAAAAAAAEBQADBgIBB//EADwQAAIBAwMCBAQEBAUEAgMAAAECAwAEEQUSITFBBhNRYRQicYEjMpGhFUKx0TNSweHwFiRyggfxQ0Ri/8QAGAEAAwEBAAAAAAAAAAAAAAAAAQIDAAT/xAAoEQACAgICAQQBBAMAAAAAAAAAAQIREiEDMUETIlFhBEKxwfAyM3H/2gAMAwEAAhEDEQA/AMasckKAuItwO1Sw6fvRFgxE0glukRlPzqGJH/ParL1biztoZriwuxb3EnlqzIEycdBk/v7Uus5tPnuTpt3JPbXTNtNzkeUh6HAHLH7iuZNs7XGhi+v2tvI4FrKw6b1fBb7c8UtbxShuVhs7aSVpCB8zgbfpgULqOkW2m6kVvtQme26BimGI9xninyQWFrZyT2sKAG3zBJPAFkx9M85yPmPpTOoiwUpOmymW/RAsUsK5UgHc/Cqe/ue1X7CqKVgtxG+W/F+QdOCfWqkubqfQI7Ay3SwTcSx21juOB0JYsvP+1JrkNZrDHcefFIh2wIy5Z0xtBI9faljbQ84pSo0uralJpds0NrFYzzKoJdQ74U5wxzgDkdMVnr4zaykt1FOpSNFaWNF2AHpnr/pV58HX6bbnUXn8puVMy8yYALYwxJUetL31GTTnks4mDQMpwqptBB788k/Ws/ofj+QLTru5F6RHcNEG/DOOGIPBFa7VvCU8KG91C403TllZW3yz85wOAqikegtAbC8Ol6LLe6rNuRZ5nBjtUbgFR3fryelW6tJcWa/DX7pI+0lZ1bcwbABUt1K9vStOO1RuPlbTT7+ewS3glv79YLCKS6kb8OMqn+K/qB2Hf+uKp1u2i065ihlvUvL5MmWKMbo4iCRsz/MfXFNtAuoYlJnuJdO82IrLO1u5ZR6JsILA9wSKE1KXRL/jTor3aAwKLGiZx+Vtqgkd+pNMkk7Ec20ooY+H9c8OTQuuo+F1ubq3XdEsV0yKR3+XB5z/AFod1uJrSW8h0oWtuz+bG9pO4aPHQMO/6Uk02AyanbzWyXFvGJFVmQHcpJwCPXOOlPL3WpdJ1cGW8F3FKu25ijkZUfjALAfUfpTtfBFP5Ftld29vcpeXKjcjbpVK5cE9CN3DE+9Pp/GVnq3myLo0S3irlJLhxIpUDHPAAPoAPvSKTxB88wlBmgnQLLE2MNjkfcetVatYxw+RMbh2guULwKxGQnQZ9P8AalpeSkm29PQBLE6OyyHdtJBI71U4ftwKJlCvIoHylohlj396FnYyOFH5B+9ZCtkQSFTtOB9a58oJ82ck1aq+TGTyS1UPIMAUyFb0cPXkLtG+5OuMVwzelRM5zTkm9nsjsxyxzRul2qzfO+eDx6UEhKuGGMg55FOore7e1SeUS+SASRGuCf7deta9GStlEkEUMwZRwpHHqa6nWWUBpgIoxgnjgj/WvVms9rNIsmeqr5mRnsc9zQVxMJEjAZcL2xQQ70ih2VmJCqAe2KlT8DuGz7V5RJjXXNan1W8aa5xGMkIkfAjGegFG6D4ouNEuIpkjS4RBhC4PyHuVPZvcVRaaXBcTJPNDItncOY4gjjIb0GeuP3pjq3haXQ/xJlYjarxpICHOf/57Y65qdxSLqM3I7uZLvV753triVDnczzXQaXaRnH79KOtdR0qOxnuZpoJL6FCYTIC6ls8fKQRn/nvSiOxDRLfX3ys/+Gv5gcHBz/pj3r3SrfRrmO4FygiukyId7Hy3Y+oH96RNNlZwnGINFqd605jv7u5W2RAAYGLqm78uB0AojRfEjaXJJcQCO6u2+VUeDCcHhmx+b1xR0P8AA9kVte/xJrWMndHb7EJlJOc+vbHJ4o6T/p4XMcNpo94C0gQ5u910V29Qg+X06mndEUpGZn1vVdZvZJNR1GZzLyw5+fH8gx2Hp09s06s4odRhl+K8uYwjchludmwdgM9fpijZtCguZha2NpdwXRkXzy08ZeGJhnJA45A7mtHe6LJDoHwekHTtN0+UYkaRBI7nuZpicf8AqoOOgpJb2Wg1FVRioYvNkFjb30KWajzJTAGQP1x1wXJxjpgZzRlxp8OgapBNqVuZQVWUAufw884BPU9M8d6VNZaZba69lNeGceXtiuNpZWJHHA6dT24x0p34P0S38QC5tNZ1Zw0SBkkVwxjRTyDu5JwOOML+1BryhlJRdNaGt74l8MSs9wtk7PEnyRSkYkPpjP1yTQ/hlbSa3vrq9NxaW4DG3hFyY1TfnDFhyT2GfTjFA+KdH8PaVfWs1oJHtYlCPFJLvMxBJ3EjkDBHAFLNSvdd1yySK2hL2FqpZUtoGjj4/mIPU/rx6UcfgGUfKoZX17a6dLHBcXwvFnYPEtnJlyDnaZpiMg5/lX39a4Xw4ZllvL2zHkzQ7rdBMcR843njngcDis81zBb3lhJMiXkcSB7iAH8PdkkJxx0I/U1sLvXrXWdMNyLcWsJJhFuJTksFGDx/KOwxj1ov2qxUs3Rib+xZZ/hUtXibkhz0de32r1tPkS+t4r2SKWBVxvSb5SgGcAnpwacwM1w9paW1qtxcK5AkcEll4wuB17/aqtQ066stU8u5s7ONpSMGNwyJwRgYJA+n9Ky5LRnwJPvYh1GRPiXjgZnjjJRGPUqDjP6V5Agk6DawGMHt6GtPs0zTY3We1jM00iiNY5Ayqu3no2dxJGc9Mdaa6hbWOqWiee09lDEGFvaeYJst0GZeSPp0GK2VmwafZ85ZnLFWPI4AFdyWkkZHmqylgCBjmtLYqIy8Dx2m0MXe+W2LSQrxlunK9v8AgpktxpsMJludOuJ7RY/LsfMbabiQ9ZGHoevJ7Cmz+BXwOL95jdMtYp7tUmbp/JjljRGoQRxCfbH5RTaFDrgsD/bimEbyw3QnWxSC6b5Ujh7984+3P1orxVoU+m2tis7yC5uIDNJDJ1ByMgfc0ctienp0ILIRNAqERtJv4Vz+bryfYUZNq7wwravEUVeqBhgnqScdTVdhJbW1uqyDE7nLN1wORyByPpVK2txqdxJ8HAJcEklB2+9HyL1H7AmIklzGvB6L60ws7NAhikIN1c/JHGOfKHdm+3avbbT2fbB5TwzbyCX4c+wHb61oLawt9NiG1Q8zkbpT+b6ZPvQlNRRoxcmVRaLpiRqrp5jDq7Pya9qyT4pnJi3bO21OKlRyl8lcF8B3/S162uzXNnd6ZDOj58lJmzCR3yVwO/frR3hzxVb6TrU763eXE5ZGimdHW5LsCMENkjBH6YpPN4r1m4E0E8cEhn2mZFAlZsHgZ5Pf1z71da2M0MtzLrllaSS5Ly200W0qrEdGBG3BPAz60z+yiX0R7WPVI7vUL/UYrZGmMtvFGq4dN3IKqAV643Ec88VZCdN/7ZL6JPhy+6GAqY12k5wT3zwMk/tSi+sLGG6SW3k+HgkXkNliT65OTii9XvY57ays7edDFCo3RCENg/5ldstjnpmhaXRXFySuxXLe/EarcFNOHw/mym3s1TKQqWJwMAZx/pXC63NHIp0+FLWREKo1uuG5GCcnn7Ufb3Mr2d9YRSbVkCfiMn8qZYAHqoP9qT/C3z2onja3wzbfLEyebkd9md2PfFOpWc7hje2NtM1SaGWPUJIZrnz12zQxgx7iPlUhu5709v8A+O6ypieBLK0Cqbe2lnAwBxhQD1JBOTyT3rNTxvZaXHEyPJeTMGcc5Vc8KB68cn7VTDfavpNw0sbyQ3CqMxy9FTHGP9q2N9mUlFLHssYJb3J+IilEgbZIo4faD69R26daYW2jQzQma4gaFkyFZuC2egZTycj0z1oc211qzfH3kU9qywl2lUgggH8xUnOPpS6Zrua4+aSTYcSkoc7m6Bgf0oJJDOTm6o1tvqZOpxmLw7d63NHErWwnjZEilA7ovBArrVdV8Ss6Xvii9uLW0mlAks7eHam3HIwOgx71To38X1eFrVdRmTT7Ub55J5nxED027WBYn0yazI1PVwtzpsVzNcWzSEGKRdysRwDhs49aKdrROcHGVM1Gt+HNJtibu5vY4jeL5ljp9vy8gPRnPRB7UPrGhW2i2tgj3fmXFwGYxRoPwUwMAk/zbs/WrJvDX8INje6nI02oFfM8iPO1Bj5MnqSDyQPpReo+GNS1XQrrxBNOPwXLS2ZBUt6hDg4yexzSyVlYTp7MTFBP5nyzsm3I37iM/p1q23e30q7WVIld0IykvzBh3qjURcLKZ3tBBbjCpHGThTjOMk5J96psjcSz58hJFlbZl1JGT0x702Mn50L6nGtpbD7i6RZ1uoIUDSZwqNvyfp/Kfam8evRQaTbSQK9vewuQY3HVuoK/r+1M7vwVFpejyanrl7b2t1KoNvYq5Taex65J6cD1pFp+mv8AD3+oy2dzcXSALbBFcoj5wXd+mAOgzyTzjFZULJt7RNPvr6zUyyzzxxyHcSZOGXJO3HcE4/Si9X1+31ARvC8qypCADdSBlUg87RjnPAA7c/YHRvDsmp38iXMxjVFLSNFjb6YB6ff2oTUbSO1K3ksjTRTSOltuUgbFONx9q2KYXPHwNbKW3uNVtLKxijSLfva9RCrj5QWyTzgYb0oW+1E3TtLua8Fq7YkmziMM2FY8glvrmuVsntfOgtpvNaSUxo4+USKQAce2M9CeKGktWt7K7tVXzJrloypx0C7ifp2oJJMMpScdIpEdhHNuuDcHBy7DByfb1rS6BrrLq8YtY3e0t0DpBKcKD0yFXg+vOfekslht0+R9Sjkt5VQmIOvEjZGACOM4Jz045oPSJnGoRBJBgqM4OAB3H7U9E8qejXpme8lu7n8Sed8jAxtHpx/XvVOpFZXcxjGzAPOc8d/0qzTw5nLnqF/Nj24qhys00turbs/Mf6Hn/nWua22Vo9DMoAByP/apXbGVWIjZtg6YXNStQckXQ+I0t7AILC2UfziBDEsh7kleSfvSW3juJrkwapNNDBvJaQhiFODzjqTxxQyHUrqzllitjJ8OdvmgjauMcAfetD/CbzXvD8D2CXcMtnE2La5jC7kBJzG4UbuvQjPvVMXWx1yQUtCLU5dLeVYLAXLxBcmSchSzeoA6D25ru1tbSKItLKwCqCwx8xbHIHoOv14qu+0Wawht5ZLhg8y7niAKNCw7NkdelAMkoHnLO8jHn5zihS6TH9WXeI+sxHcwJZpLaRmVskyj54WHpz3x2Ge1c2Wkai73XkyRyrA+xJeVWX/xLCg5tA1u3eKe+0e98jO9GMLMjjr1T29DXui3InLWRe4ji8zzZER9jL2G0nnOD39+KZcdLZJ87k/br+RlBDG6Nc3V7BbOI8RpuLFjnnIzxnk/YUDPZy3NlLfSX9h5SybPLjb8XJzjKnnHHGTRMulX09wItPshbNGgMcc65aRXBGSec8Cn2jf/ABpdA201wdrINxUqGGe/1/2rUorbB6mT0jLpeT6zqQeSzeQ+SI5DaRkHAGM8Ajp6j9KZ2ujRzwPBYW9+ZAQC8xACc8jbjj9TX1nRvC1vpSlowQSuPl4x69P+c0xjtYIFZYoVXJzwtRlN+B9dWfPNKttSgu2iubJItNFvsjgRSQHA4b/yJ6k560KugtHqJvk09bgjLLE6sAsnUOcHB57YxxX05yiuVZBtI9K882MMOB6VP1JhpXaPkrx+Ibu9nl+BdJZN0iyq2dj+vPuK61jx3r7qNK1rTrRnQq8jBCPOA5CkZHcjpjOMV9YzDtO1R6YpNdaRY3upxTSxgypxvHX6Uy5X5QMF4MDe3dt4i034j+FFbhQQlsygIDgZlcgDcTkgL2H1rnw34Y1qVop9QsPMMB8y1tZciJT/AJiByw9sj61s/G1rbWNpZwRRogk3ZK8en96A8N+OG0V10rxI5aL/APWvT1K+j/T1roi20QdJmQ1Xwp4l1LVjf6xmaR8AvjhRnhQOwHpWj1eOW10y1sNOjfMMaiaYp/NjkL7cV9It76C5IEZjdTyCvIqyayt58bo1OOmRRu+gXTPi2l3NzqN4umSzSNC+Y3ixhTz+XjB/QilHijTJYvEEMerXay2MaFY1s4wuxF//ABonY+/PrzX1TXfB0DB7uwZ4LhRlGQ4wa+eyveX10bLUrSJJoZFZplPACjHA9T3+tCCcRuTGexTJr73V+l3bxQwx2ZEdjZPhljXGAfcjGSfp60x07w1qeqWbzxBpGcgxqqk+b/mI9uP+ChvGUhihtISqxkMx2qoG2gbLWpLKLT0tXkQu584QSFGYElcbuoyM1pLJlISUI3Yff6VrbxfCXKs0aZwmwArnqPXpWcsojaahtVzz8hYoQAT1GDTeyja1v5YJbmVI5mMmxmLK3Jx3IP1qS2kZv5fjVkil8xSkjdCo65B6g9jTWoqick+TdD61hMbL+dndQG57CuI1YPLdMQ3BUED16VbwCzRJjeoUEnkZOK7njWLy4z+VeAd3U81zocvtoYxAm5BnHPzCpSPzLqUl45pFQk7RjPH6VKNBtDO00bV9N8KjULdpo9Q1WRVsIUCec8QwWYg8ntgDGM89aU3bX6s/mWGqWk6qI2kuZ5GaZuhPzjj6AVodY1GSTWdLuLCO0ntLdCFgWTMquwUsWBGegGBnvTDxl4simsoGvrt477aHjhtI4wF4yquzKWU/Q/pV70Rpt2fOBNHe6ht1C8dII12NO4Z8AccL16447e1NJdNtRFJ8De298VUkKY2QAdCQCTn6Vnr8yMplkheOZsMT/nHqff3orQJ5XivYo4lMkka+UzDIjw2SR7nGK0tK0GLuVM0fhHXbqCdNCnklcrxA6knjrg88AfoOa1Cxr4plutMhsgZYHUR3yjHmcnd0GCOO/rSLwbpeoXeqRRrYROpj/GuY48MoPBUsTX3HS9MtNHtFjt4ljO0ZPUn6mkyckGsH+wh8L+Do9HtkE8wdhnj0rRM0MfGOgxQ93ehfpS+4u2JG3kNQSsDb8jJ7uIZAIxj1oGW+jHpSWe5bew3cg0JJOTwXp/TsTNIcy6hbu21mAx3FL7u42flbI6g5pDeNKAxTPIpT/FJIpRFLkD1NTlx12VhyX0a+O+yPeube9PxmGwFUE5rLpqS+Zw2fTBruPUGN0QBxt5qdKymWhp4z83VrSGO34ljbOSMgA96zupWEFxZ+QzK2BhGPQnFGi8KXB83GHUqN1DTxPMfKto//AGA6fWrZJaOfzYw8B3PwSTQ8hQ3yqT+X2FbqLUwQOn61gLG3ezUg5Lk8tjGabWszhMlqaCfk02ayW7SSLljisR4igSKZXTaIw29jjrjpn74ptJcMqnDcdqR6vdM0bbRuNNKIIyMD4vuA9zB5gLquWI3Y/T9qCsLvS01BHuY3W243pDgs5+/HWtXr+i/xDw3FdRQBZINxmnz29x6Cslp2mpG5+OlQ2zK2zY3IfHBrWqpjVJu0ivy01rxFIbKBrWByW2b9xRFHOTxknH6mrdQfbe2tlHJJMqbQHdiflHIA9qL08W+li9eOR5viITBgnHykg5yPTaP9qpRlluYVIwgYDKDpSynHorH8bkUHJ6NdGnllHlZSqDzCSe/v7VVJslJmZtoTJYE/rz9Kvkci3cICWI7cn60g8TXYjj+HHCsuF56+vH/O9SirYG6AptY2ysEllZc8Hdtz9u1SkRuOT8x/Uf2qVfBEsjaayY7toItYkFu5iM84EgDO7YCgdjjHf171XpGpabo2pW19p1qzC3DNM5IYEEehHH0yaZ+GfGVnoGnXzS2+++BMdu2E/wAM88cZyOvJ70o1Txdaarc3NxLo9sZHjKq2drux6ltuN30pMWUzV0jeaX4r8N+Jo5bG90qWSZ0Lqk0Ua+YvJLZB4xj+mKxmryacmoQwaVF8JbhcLbxx5Y4PVm9+9c6DrWnWWlfD3qyLIFYBrcESRhjnaG7g91JxTrwtpGm3d/b3Uckstu8uXOcEHPcGhNX/AMGinHZ9S8DaetlokMpyDIu7btCgfYd6Y6hcn8vQn0oiWeKG3CoAqgYUDoKy+pXxaF5Ub8zYyPTOK0UkqRKTbeTJcyKXwJOnrS173aWAfP05xQTTs0m3dgn9qpkGXC+YR6+9WjEk5Flzc+YcRgL3zurmKRiE3pkv3zVStHFuXcpJGQSOterIsvEQ2bueB39qpQlhwXd+YChLqwhlB3r19qOtIWGAcgep70etoGH96Lp9i2fPL7Qp4cyWshPPCkUPos89xqQgZArgZce1fRbnTtyccE9KSTaDdW1/Hcw2xJAwxTuKhLjjdovHkbVMYWOl2ww9ygkx0B7VbcR2ilmhXYe+KHMOoSxZit5fuMf1pfc6frDq22Ir685/bvQx8hy8HN7c46AkVxY3AkAKk49D60i1QaxYKwKRzgc4Rirj7ciuvDt692rGRSsvOUxyv1pl2CXQ51C/EAVS3J7UIl0sg3OQPocUi8VaibOSNgAzAn5D1pRBqhuSzzSIiqOIkbk/U9qLAt6Nhc3SyWlxaGRhFIpGN3B75/pWCgljS+ZLiRG8p9mRyH5xkj0oTVruVrmMiRsryMHpU02Brm/LPjCgyP78ccfXFK0mrHjJxlihlPG0eQABgmuLYsJEYJn5hyvXrRkmrSeSYru0trmNckS52yL3PTrz617Bp7pFaXtxNEltMcgiUHp1GAcg/Wo40rO189vH4HlzdxpKyFCGEZcEDoM4/vWT1Kd5rp5NzYPA3HsKY6xKI55SkhwQEznPvj9aQuhOfn3fYj+lPCNI45y2egx4/wAOP9KlckkH8oH1r2qC2aDQbbw3fyvDqnxNvCSR8YrfMc55KDgKOMkZ7Vo7L/440a8nY+H/ABTY6jPACwgQjc/1Of3xWIkhlF3CssYRVQ5yAvy+n60C9/MtwrW0zwspwrI23H3HSsregyil7jU+IPCmp2cfxV/Y3dsq5Ut5fyn3LDih9C1k6QyQuCId+SxPKjueOtMrvx74nu9MmsxfC+RkMb7bdSgBxyTtG89gf68GlllmOIeZBaup/wASO6hWTbkfynqPsRUpe3tnTwpzTSVs+u6T4ptNQvLyyA/Gi/Ej3AjdGeOO+Rxnp1FJdZ1B7e+lgkCxqwLxouBgsc5x9cn9az2mfweEwTLfjS7og7XeN2hkHfjLOpHT0OPencGm6Pf6ddao2s2+pXEa7VEbNbEDsCCCSfQYHWgpIXk42ntA0eoGGNXlikzIf8QsoXGcZOT3qQ38TCaVpkPlnBIbIx7GsfrV5NbxRWTxOk0bAzFsfKN2QPrml9nqFxKRpyQGQ79seCcqck444PenjOVWK+KGST0bvSrsapcSiEkRQ4G7HUnnj/nrT22iJasZ4e1Wx0m1Hx0rwTO5HlhSTjHoPfPWtnpV7b3UKy253I3qMHNPCWtkOWFN49Dq3AjTc5r2W+2gAAf61m7zW4438uMs3soJP6daVHxRbqjSBJHmGdiHAH3OeK0uSK8mjwzl0j6LYQs582dhjrsHJFVXeuLbO4iQLjj618qu9U1XULzzEZ1dSvlxxA4XHTjufrVVpqOp3Nzlb13OfnLBcDHXtgGoLmyfR0v8bCNtn1iDWxLEHaME9ieK8fU7h0yxjjU9M9celZbSzZsd08jSsvPykhR7Z60yu9Qtm3MkeVAxnGF/U1RuiSjYLfkO7PvDEHoFNKLfTYDbXgZii3AYfI2CuR1yO9A614gRZmEcaIqEAuQc9jwP70He+I5Vs91inmO/QNwP96MOWL0x+T8XkrJIxOs2d1aXbJdzNMR+R3bcWXseaCtXZJx+MYgeGcdh3pjf6hc6jiO7iRGjUkME2nA7UqWNnztHy55NVOWQylthcxz3dvtFvHKEQEYbBzj/AE/WnEd3aJpSW1hDIJZHHxEuDl/8q46daVaPp15dvJDbxb9jA7zgKG92PA4zWn8N2XwupfEhvMS1HmTFAdivyFX65I/c9qnMvxLLZmp7q123AkRln9VYjefQjpjp+9AmaaCPylJEZ+YDGR05ppeWnxtzN8K6L85Zl39cnrzR174d1LSQJLiOG5tJYQC6knyg2MMcdCPXmimhJwknbElreTXEgErDagLkn2Gcftih/PLHuCecjjmoq+TJJG/OQVXB6+nNeWllPdyKsK5ycZJAApqJ2+jkuueZXz9P96lPB4TuCObiHP8A4mpWtBqRo4dJutZs40W1aWZACTC3mB1bryM9eufakmq6fY6NLNbeUfig2HSYH8IY7Z7/AFpNBe6ho9x/2960UiEf4MhIGPTHFUX19cX0zTXMnmyuxZ5G5ZifU0mDvssuWCSbXQwN+sWdmGUjgDqtC/HXBcsu7b/lxwKusdHW6gWV7qG3yCNrSDcSPUdQPrQciNb+YrMcglTn+lZccUM/yOR+aLYryRm/EYrGR8xUDNMvibFLdUVJJd/JZwEZW9VI6fehtIl02KSePUo5Jopodu6MYaJ8ggjP0+9ewLbXLeVbrO4Q8nIDbe/A4FCSSNxzlN03Zel20lvKJQJVJGSMk59wakcFimnTlmZbltpR0bgEHkYH2Oa9cbAfI37QcgPxxXM9peyWjSRlfh1frjpnjJ78k1OL3o6JxWKz2yyO8jSOGGVvOlHSSTAAHX749TV38dm05yh87fgfMDyO4wft/WhpNPWS2szFyw+WRieOpJoueCOdsTLuAzgn0oTcVJWDihOcHiqo1fhPTpDp8mu6lFIkEoMVtFKmWcn+bAH5emDWX1WK5tr69hhiRYUYbWIOVPGR1/0Nbt/EcVt4VhtrZYEuVteTPL8lsmCN3qScHaB1NZY29pb6VEfit90JC8s0jeZG6+xB69c578Yo0q6J5yurFE86WsJkkunWVvyLGOe4JJ7d8V1tu7COES2t3GkzBVkeMrvJ57968SO1bUBOuWRHyoB4BB6819Gs/G2nat4evLTVgyskLKWKqGLY4I9yRTKo9Gm3PZjor3U7ZN8MUkUIAJfIbdnpXg1Is6z3d0PLLBcMcuwPdV4BFJdT8mNZ001XW3ifrLKxMwzjOOBj6DNcrFdfEQTXU23T1k3BhggAnnA+mcHmjViOSj2ca7c/FXL+SGQEj5XOWAx3OKotBcBMLMHAP5WXIzTNreyvdQkaCZmhOQhV+fYEkc8ewoW7t2tjutnJHQ56/rWUKVFJc7cskyiS+YSlXjUgDax6g/rTbQLXSp2B1IxukPzKrMwynYcdSPT/AEpE06xRl9i7nYZfOSvXj74rT6faKIra5j5VwC0s7L5R9gD9DxjIrViCU3yv3bZqvJ0G90dt5a0sIiC7jKZGeFVQASSeB/tSF78fwu/Wzt0gsUVnWMMx6DksWJJJOPuRQGr6q93CIvP/AO2hYsiRxBE+oH9/WlV/qFv5Mltbz3At5McHHzjrz6c0iblL6KyguKG+2KvOySy5Vvr0rQ6brGqfwto7W8dX3CLoCCpB65Hb9festL5YP4bH3zRtjdi3gDupkjD/ADRq+wtxxzg1bH4OWXJbqQVe2iw8rLA8hBVgOefVRSpPOt23AMNp6ijZr+3uJo0itVs4sjc/mPIw9+Tj9AKbS+FLuWBbjTZbe9xklrWfLP8ART6fU0VonJxfQvXW7wqCEcj1APNSirbUNQsoVt5baw3pncLmI+YOc4bpXla0bGX9QqvfIebZFAICfSQsAPvk/vVQtX/NCd5zwF5rT+IPB50Tw/HrTahDcQXTotsIuWbIyS2CQMY/Ws1ZyKkjbrfzgo6kkbeeT/8AdG9Cay2cW8t1bzYiMiytkbVHXPt3pvCA0ge/hR3VcKrdM47+uPSnumXFqlncXFtFc2U88RVboEyEg+zA/L7j9axcsji5JhmYlWwjqSpI9fak3LrRVNcfas0FzFbz6e/lWJ80d4kYlQO5x2pVpx8nUo9x2bgQfYEUfpskNjG2WaS6kUh5A+MA9gev3oWeA393DHbH5nO0kk9PehGW8RpR1kg3SdN1jWp/L06zleLJ3TbMIoHXLdP3pu4eKI24hmdx+G4RciUdv9DmrpvEDaKg0+GKRIWAV1U7UK+1X2HiDS3nRpWeMKuWDLgYBHT1PJ/Sg0hlm9mfmeLyUSwzJcE4SMsOMnPIJ/v1oGHUSbpYL5JIfmKyY4Iz2welaPx1b6bHd/EaVNbSLIqsI0fLA+uR/TtWWlv3kkV78i4mQ/nl+bcOMAnvQUYtbRRz5I6UjQ3V+vwDJIxFkWAC/wAsnHU+tIpJpZ/ktFAiBywJ2q31o60mbVhL8aAtunKRIhIBxgcZH9aZ6E1lF8Vqer5NrbIBFGq7vMlb8q49gD+x7Vn3S7F47q30eJ4du7Tw/dX97ENqSR7GjORGrHknHfpx2zRcFqskEws7LMsUW93/AMTOBnlR2HHFajXtThk8D2enyWotJ7vaZII2zhVPzZ74J4rGfEMkoihALnaFGeMnoftikz91UOuO45WKtCvDNcC3mO0M2dxXOTnPIom7ujcTRpFHIYCQFQLlmUkZH35rm3voX1OKW1t1ie3Y/Dlj07gn3zz0q6wmmttahaPcJDIoUYx14wPbmi2r0FJ072KruyGlTyPbs7SI2fJdMNFjH5v1NU3Gomb5/wCY/wAvrWg8a2kdvrtxFkI0sas+B+UkDI/bt60JaCzTVpJlt4oHGNsKMSEzk71J69hj39qrGSfZDk4nCmhR5SeQJL1QqSEBdo+fHciiJr5NzNGJEGzYm7+Veyj2p+NPuvjrhP8Ap69u7Peyedb2sj7yDywONpGQf0oTUdFti0kMD3MEkXDRzxkbPv2H7CtKmLCTi7M/JdM0GwYFd26bwpmRXjzjJz/euJ4oLWYxzJIZFxujJxz9R61Yl1CxAhRxk8Rg5/4ayVdDT5HN+4YxW1hKUWKGPLEKoHJzWot9A0HThHFN5V7M75cO22KM+g7tjP04rK2alrdcrgZ4B6r9fer9RnuJbaKKJgAnAUIAPr6k1NybdFFxxS6Ltdt/DriT4K3khn3cGIkJ+hJ/aqbPxBqVhZrYWt0REB8meSP9qUSw3KEqwbefmwrA0fpWh3uopJJbLnyhltzYP2HejTrbHuEeo7CGWS9Jubi7jEsnLb1yalVvpd4rYTzCPUR/7VKXBh9VH1v/AOP4Yn8KaXZSRq9vcLtlRhkNkc0r13TrLw3cmLRrWK3R4o7phjdmQsV754A6DoKlSmb7OVLaMxq+t6hf3iRXM7Msa5/8iwBOf2H2rH6qqreFUAUEZOBUqVSHQk+wCZmVuCeRimc95cRw2brJj8MYGBgVKlAeHY1jlN3bh7gB8oeCOBms8SU3Y7MRUqUi7L9IGlcq2BwCOapLE9alSrxOObdm3tVEUUMKD5Aitg9yRXXhjToNQ1u20+43G2bUU3ID1xipUrjg/fI7Z/60EeI9Zu9T1O5lnESGKR4kWNNoCqxx/U1kra9uINT+IilZZVfhh7VKlPx9sPJ1EtvHMtxPI+M7yOOnStnb20Uk+jXBGJUngZWHbLrmpUoyN+kQa7NJdapd3M7FpWmbn0AOAB7ADFO9P02Gey0S+ct529ugGOD9M1KlJHspzf4Ic+NrYafodvqlhNcW11POscpimYB8rnJGevuKzWgXs9/I1jeN5sLKzjd+ZWHcH7V5UrMaKWDFs4hu7qdZ7aEsP5wuG6D0rrTtKsp7eVZIQcZwcnP61KlVt2cuMcejyWMWqyrEThBEBuOfzDmuMeYACT83Bx9KlStQGxVFK4cqDhRkgCtV4NvZhp4hyCs91855BwAvGR2rypWl0D9SNl/AtP4/CccDpK396lSpXNbLUj//2Q==",
                    title = "Book 2",
                    updatedAt = "2023-02-02"
                )
            )
        )
        val categories = mutableListOf(
            AboutBookData.Data.Category(1, "Novel"),
            AboutBookData.Data.Category(2, "Fiction"),
            AboutBookData.Data.Category(3, "Japanese") ,
            AboutBookData.Data.Category(4, "Japanese")
        )
        binding.aboutBookRv.layoutManager = LinearLayoutManager(context )//,LinearLayoutManager.VERTICAL,false)
        val adapter = HeaderAboutBook(requireContext(), dummyData)
        binding.aboutBookRv.adapter = adapter

        staggeredGridAdapter = StaggeredGridAdapter(requireContext(), categories)
        binding.categoryRecyclerView.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.categoryRecyclerView.adapter = staggeredGridAdapter

        binding.categoryRecyclerView.post {
            val itemHeight = staggeredGridAdapter.getItemHeight()
            val totalHeight = itemHeight * adapter.itemCount

            val params = binding.categoryRecyclerView.layoutParams
            params.height = totalHeight
            binding.categoryRecyclerView.layoutParams = params
        }
        observeBookData()
        observeError()

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.about_book_actionbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            R.id.home_bar -> {
                Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.seating -> {
                Toast.makeText(context, "Seating", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.exit -> {
                Toast.makeText(context, "Exit", Toast.LENGTH_SHORT).show()
                true
            }
            /*
            binding.home -> {
                Toast.makeText(context,"Home",Toast.LENGTH_SHORT).show()
            }
            binding.seating -> {
                Toast.makeText(context,"Seating",Toast.LENGTH_SHORT).show()
            }
            binding.library -> {
                Toast.makeText(context,"Library",Toast.LENGTH_SHORT).show()
            }
*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeBookData() {
        viewModel.bookData.observe(viewLifecycleOwner) { book ->
            book?.let {
                val thumbnailUrl = it.data?.thumbnail_Link
                Glide.with(this)
                    .load(thumbnailUrl)
                    .placeholder(R.drawable.loadingimg) // while my pic loading to apearce
                    .error(R.drawable.errorimg) // to appear if their is an error
                    .into(binding.listenBook)
                binding.bookName.text = it.data?.title
                binding.authorName.text = it.data?.authorName
             //   staggeredGridAdapter = StaggeredGridAdapter(requireContext(), ) //important
                binding.categoryRecyclerView.layoutManager=
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
                binding.categoryRecyclerView.adapter = staggeredGridAdapter

                /*
                 binding.gridRecyclerViewBooks.layoutManager =
            GridLayoutManager(context,1, LinearLayoutManager.VERTICAL, false)

        val adapter = AdapterHome1(requireContext(), dummyData) { book ->
            onBookImageClick(book)
        }
        binding.gridRecyclerViewBooks.adapter = adapter
                 */

                val truncatedText = it.data?.description ?: "Descraption \n read...more"
                var fullText = binding.description.text
                fullText= it.data?.description
                binding.description.setOnClickListener{
                    if (isExpanded) {
                        binding.description.text = truncatedText
                        binding.description.maxLines = 5
                        binding.description.ellipsize = TextUtils.TruncateAt.END
                    } else {
                        binding.description.text = fullText
                        binding.description.maxLines = Integer.MAX_VALUE
                        binding.description.ellipsize = null
                    }
                    isExpanded = !isExpanded

                }
                val categories = it.data?.categories ?: emptyList()
                (binding.aboutBookRv.adapter as? AboutBooksAdapter)?.updateBooks(categories)

            }
        }
    }


    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), "Error Book: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }



}